package br.com.myrecipes.domain.usecase

import br.com.myrecipes.domain.model.RecipeModel
import br.com.myrecipes.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetAllRecipesUseCase constructor(
    private val repository: RecipeRepository,
) {
    suspend operator fun invoke() : Flow<List<RecipeModel>> = repository.getAll()
}