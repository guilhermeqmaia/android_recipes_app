package br.com.myrecipes.domain.usecase

import br.com.myrecipes.domain.model.RecipeModel
import br.com.myrecipes.domain.repository.RecipeRepository

class InsertRecipeUseCase constructor(
    private val repository : RecipeRepository,
) {
    suspend fun invoke(recipe: RecipeModel) = repository.insert(recipe)
}