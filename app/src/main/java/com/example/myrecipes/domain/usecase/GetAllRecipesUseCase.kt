package com.example.myrecipes.domain.usecase

import com.example.myrecipes.domain.model.RecipeModel
import com.example.myrecipes.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetAllRecipesUseCase constructor(
    private val repository: RecipeRepository,
) {
    suspend operator fun invoke() : Flow<List<RecipeModel>> = repository.getAll()
}