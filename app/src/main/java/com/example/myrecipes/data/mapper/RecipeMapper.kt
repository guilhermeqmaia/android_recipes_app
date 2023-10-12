package com.example.myrecipes.data.mapper

import com.example.myrecipes.data.entity.RecipeEntity
import com.example.myrecipes.domain.model.RecipeModel

fun RecipeModel.toEntity() = RecipeEntity(
    id = id,
    name = name,
)

fun RecipeEntity.toModel() = RecipeModel(
    id = id,
    name = name,
)