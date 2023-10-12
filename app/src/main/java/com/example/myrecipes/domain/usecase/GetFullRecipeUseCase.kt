package com.example.myrecipes.domain.usecase

import com.example.myrecipes.domain.repository.RecipeRepository

class GetFullRecipeUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipeId: Int) =
        repository.getFullRecipe(recipeId)
}