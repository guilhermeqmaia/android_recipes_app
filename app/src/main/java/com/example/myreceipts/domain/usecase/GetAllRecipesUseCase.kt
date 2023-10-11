package com.example.myreceipts.domain.usecase

import com.example.myreceipts.domain.repository.RecipeRepository

class GetAllRecipesUseCase constructor(
    private val repository: RecipeRepository,
) {
    suspend operator fun invoke() = repository.getAll()
}