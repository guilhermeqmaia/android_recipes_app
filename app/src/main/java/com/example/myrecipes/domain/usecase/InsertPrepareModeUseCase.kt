package com.example.myrecipes.domain.usecase

import com.example.myrecipes.domain.model.PrepareModeModel
import com.example.myrecipes.domain.repository.RecipeRepository


class InsertPrepareModeUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(prepareMode: PrepareModeModel) =
        repository.insertPrepareMode(prepareMode)
}