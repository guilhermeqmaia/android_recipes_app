package com.example.myrecipes.domain.usecase

import com.example.myrecipes.domain.model.IngredientModel
import com.example.myrecipes.domain.repository.RecipeRepository

class UpdateIngredientUseCase (
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(ingredient: IngredientModel) = repository.updateIngredient(ingredient)
}