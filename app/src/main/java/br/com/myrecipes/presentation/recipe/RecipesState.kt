package br.com.myrecipes.presentation.recipe

import br.com.myrecipes.domain.model.RecipeModel

sealed interface RecipesState {
    object Loading : RecipesState
    object Empty : RecipesState
    data class Success(val recipes: List<RecipeModel>) : RecipesState
    data class Error(val message: String) : RecipesState
}