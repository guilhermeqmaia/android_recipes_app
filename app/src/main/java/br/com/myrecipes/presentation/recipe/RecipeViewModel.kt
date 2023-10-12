package br.com.myrecipes.presentation.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import br.com.myrecipes.data.db
import br.com.myrecipes.data.repository.RecipeRepositoryImpl
import br.com.myrecipes.domain.model.RecipeModel
import br.com.myrecipes.domain.usecase.GetAllRecipesUseCase
import br.com.myrecipes.domain.usecase.InsertRecipeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.onStart


class RecipeViewModel(
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val insertRecipeUseCase: InsertRecipeUseCase,
) : ViewModel() {

    private val _state = MutableSharedFlow<RecipesState>()
    val state : SharedFlow<RecipesState> = _state

    init {
        getAllRecipes()
    }

    private fun getAllRecipes() = viewModelScope.launch {
        getAllRecipesUseCase()
            .flowOn(Dispatchers.Main)
            .onStart {
                _state.emit(RecipesState.Loading)
            }.catch {
                _state.emit(RecipesState.Error(it.message.toString()))
            }.collect {recipes ->
                if (recipes.isEmpty()) {
                    _state.emit(RecipesState.Empty)
                } else {
                    _state.emit(RecipesState.Success(recipes))
                }
            }
    }

    fun insertRecipe(name: String) = viewModelScope.launch {
        insertRecipeUseCase.invoke(RecipeModel(name = name, prepareTime = "45 min"))
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            val application = checkNotNull(extras[APPLICATION_KEY])
            val repository = RecipeRepositoryImpl(application.db.recipeDao())
            return RecipeViewModel(
                getAllRecipesUseCase = GetAllRecipesUseCase(repository),
                insertRecipeUseCase = InsertRecipeUseCase(repository),
            ) as T
        }
    }

}

