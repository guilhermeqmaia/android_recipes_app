package com.example.myreceipts.data.mapper

import com.example.myreceipts.data.entity.RecipeEntity
import com.example.myreceipts.domain.model.RecipeModel

fun RecipeModel.toEntity() = RecipeEntity(
    id = id,
    name = name,
)

fun RecipeEntity.toModel() = RecipeModel(
    id = id,
    name = name,
)