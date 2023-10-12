package br.com.myrecipes.domain.usecase

import br.com.myrecipes.domain.repository.RecipeRepository

class GetFullRecipeUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipeId: Int) =
        repository.getFullRecipe(recipeId)
}