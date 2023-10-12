package com.example.myrecipes.domain.usecase

import com.example.myrecipes.domain.repository.RecipeRepository

class GetAllRecipesUseCase constructor(
    private val repository: RecipeRepository,
) {
    suspend operator fun invoke() = repository.getAll()
}