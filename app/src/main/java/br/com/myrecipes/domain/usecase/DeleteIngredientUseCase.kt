package br.com.myrecipes.domain.usecase

import br.com.myrecipes.domain.model.IngredientModel
import br.com.myrecipes.domain.repository.RecipeRepository

class DeleteIngredientUseCase (
    private val repository: RecipeRepository
        ){
    suspend operator fun invoke(ingredient: IngredientModel) = repository.deleteIngredient(ingredient)
}