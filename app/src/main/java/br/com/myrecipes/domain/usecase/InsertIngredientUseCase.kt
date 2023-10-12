package br.com.myrecipes.domain.usecase

import br.com.myrecipes.domain.model.IngredientModel
import br.com.myrecipes.domain.repository.RecipeRepository

class InsertIngredientsUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(ingredientDomain: IngredientModel) =
        repository.insertIngredient(ingredientDomain)
}