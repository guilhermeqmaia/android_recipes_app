package com.example.myrecipes.presentation.mapper

import com.example.myrecipes.domain.model.IngredientModel
import com.example.myrecipes.domain.model.PrepareModeModel
import com.example.myrecipes.presentation.model.ItemListModel

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