package br.com.myrecipes.presentation.mapper

import br.com.myrecipes.domain.model.IngredientModel
import br.com.myrecipes.domain.model.PrepareModeModel
import br.com.myrecipes.presentation.model.ItemListModel

fun IngredientModel.toModel() = ItemListModel(
    id = id,
    name = name
)

fun List<IngredientModel>.toModelIngredient() = map {
    it.toModel()
}

fun PrepareModeModel.toModel() = ItemListModel(
    id = id,
    name = name
)

fun List<PrepareModeModel>.toModelPrepareMode() = map {
    it.toModel()
}