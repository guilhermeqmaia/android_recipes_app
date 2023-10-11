package com.example.myreceipts.presentation.recipe

import com.example.myreceipts.domain.model.RecipeModel

sealed interface RecipeState {
    object Loading : RecipeState
    object Empty : RecipeState
    data class Success(val recipes: List<RecipeModel>) : RecipeState
    data class Error(val message: String) : RecipeState
}