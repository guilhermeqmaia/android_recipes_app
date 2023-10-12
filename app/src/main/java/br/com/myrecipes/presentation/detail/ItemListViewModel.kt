package br.com.myrecipes.presentation.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import br.com.myrecipes.data.db
import br.com.myrecipes.data.repository.RecipeRepositoryImpl
import br.com.myrecipes.domain.model.IngredientModel
import br.com.myrecipes.domain.model.PrepareModeModel
import br.com.myrecipes.domain.usecase.DeleteIngredientUseCase
import br.com.myrecipes.domain.usecase.DeletePrepareModeUseCase
import br.com.myrecipes.domain.usecase.GetFullRecipeUseCase
import br.com.myrecipes.domain.usecase.InsertIngredientsUseCase
import br.com.myrecipes.domain.usecase.InsertPrepareModeUseCase
import br.com.myrecipes.domain.usecase.UpdateIngredientUseCase
import br.com.myrecipes.domain.usecase.UpdatePrepareModeUseCase
import br.com.myrecipes.presentation.mapper.toModelIngredient
import br.com.myrecipes.presentation.mapper.toModelPrepareMode
import kotlinx.coroutines.launch

class ItemListViewModel(
    private val getFullRecipeUsecase: GetFullRecipeUseCase,
    private val insertIngredientsUseCase: InsertIngredientsUseCase,
    private val insertPrepareModeUseCase: InsertPrepareModeUseCase,
    private val updateIngredientUseCase: UpdateIngredientUseCase,
    private val updatePrepareModeUseCase: UpdatePrepareModeUseCase,
    private val deleteIngredientUseCase: DeleteIngredientUseCase,
    private val deletePrepareModeUseCase: DeletePrepareModeUseCase,
) : ViewModel() {

    fun getRecipeWithIngredientsAndPrepareMode(idRecipe: Int): LiveData<ItemListState> = liveData {
        emit(ItemListState.Loading)
        val state = try {
            val fullRecipe = getFullRecipeUsecase(idRecipe)
            ItemListState.Success(
                ingredients = fullRecipe.ingredients.toModelIngredient(),
                prepareMode = fullRecipe.prepareModes.toModelPrepareMode()
            )
        } catch (exception: Exception) {
            Log.e("Error", exception.message.toString())
            ItemListState.Error(exception.message.toString())
        }
        emit(state)
    }

    fun insertIngredientsOrPrepareMode(
        typeInsert: String,
        name: String,
        recipeId: Int
    ) = viewModelScope.launch {
        if (typeInsert == "INGREDIENT") {
            insertIngredientsUseCase(
                IngredientModel(
                    name = name,
                    recipeId = recipeId
                )
            )
        } else {
            insertPrepareModeUseCase(
                PrepareModeModel(
                    name = name,
                    recipeId = recipeId
                )
            )
        }
    }

    fun updateIngredient(ingredient: IngredientModel) = viewModelScope.launch {
        updateIngredientUseCase(ingredient)
    }

    fun removeIngredient(ingredient: IngredientModel) = viewModelScope.launch {
        deleteIngredientUseCase(ingredient)
    }

    fun updatePrepareMode(prepareMode: PrepareModeModel) = viewModelScope.launch {
        updatePrepareModeUseCase(prepareMode)
    }

    fun removePrepareMode(prepareMode: PrepareModeModel) = viewModelScope.launch {
        deletePrepareModeUseCase(prepareMode)
    }

    class Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            val application = checkNotNull(extras[APPLICATION_KEY])
            val repository = RecipeRepositoryImpl(application.db.recipeDao())
            return ItemListViewModel(
                getFullRecipeUsecase = GetFullRecipeUseCase(repository),
                insertIngredientsUseCase = InsertIngredientsUseCase(repository),
                insertPrepareModeUseCase = InsertPrepareModeUseCase(repository),
                updateIngredientUseCase = UpdateIngredientUseCase(repository),
                updatePrepareModeUseCase = UpdatePrepareModeUseCase(repository),
                deleteIngredientUseCase = DeleteIngredientUseCase(repository),
                deletePrepareModeUseCase = DeletePrepareModeUseCase(repository),
            ) as T
        }
    }

}