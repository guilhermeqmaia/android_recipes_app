package br.com.myrecipes.domain.usecase

import br.com.myrecipes.domain.model.PrepareModeModel
import br.com.myrecipes.domain.repository.RecipeRepository

class UpdatePrepareModeUseCase (
    private val repository: RecipeRepository
        ) {
    suspend operator fun invoke(prepareMode: PrepareModeModel) = repository.updatePrepareMode(prepareMode)
}