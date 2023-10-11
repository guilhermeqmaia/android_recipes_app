package com.example.myreceipts.domain.usecase

import com.example.myreceipts.domain.model.RecipeModel
import com.example.myreceipts.domain.repository.RecipeRepository

class InsertRecipeUseCase constructor(
    private val repository : RecipeRepository,
) {
    suspend fun invoke(recipe: RecipeModel) = repository.insert(recipe)
}