package com.example.myrecipes.domain.usecase

import com.example.myrecipes.domain.model.IngredientModel
import com.example.myrecipes.domain.repository.RecipeRepository

class InsertIngredientsUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(ingredientDomain: IngredientModel) =
        repository.insertIngredient(ingredientDomain)
}