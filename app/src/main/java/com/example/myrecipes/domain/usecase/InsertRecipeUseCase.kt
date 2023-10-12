package com.example.myrecipes.domain.usecase

import com.example.myrecipes.domain.model.RecipeModel
import com.example.myrecipes.domain.repository.RecipeRepository

class InsertRecipeUseCase constructor(
    private val repository : RecipeRepository,
) {
    suspend fun invoke(recipe: RecipeModel) = repository.insert(recipe)
}