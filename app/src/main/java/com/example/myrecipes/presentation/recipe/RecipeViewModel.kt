package com.example.myrecipes.presentation.recipe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.myrecipes.data.db
import com.example.myrecipes.data.repository.RecipeRepositoryImpl
import com.example.myrecipes.domain.model.RecipeModel
import com.example.myrecipes.domain.usecase.GetAllRecipesUseCase
import com.example.myrecipes.domain.usecase.InsertRecipeUseCase
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val insertRecipeUseCase: InsertRecipeUseCase,
) : ViewModel() {

    val state: LiveData<RecipeState> = liveData {
        emit(RecipeState.Loading)
        val state = try {
            val recipes = getAllRecipesUseCase.invoke()
            if (recipes.isEmpty()) {
                RecipeState.Empty
            } else {
                RecipeState.Success(recipes)
            }
        } catch (exception: Exception) {
            Log.e("Error", exception.message.toString())
            RecipeState.Error(exception.message.toString())
        }
        emit(state)
    }

    fun insertRecipe(name: String) = viewModelScope.launch {
        insertRecipeUseCase.invoke(RecipeModel(name = name))
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

