package com.example.myrecipes.data.mapper

import com.example.myrecipes.data.entity.FullRecipeEntity
import com.example.myrecipes.data.entity.IngredientEntity
import com.example.myrecipes.data.entity.PrepareModeEntity
import com.example.myrecipes.data.entity.RecipeEntity
import com.example.myrecipes.domain.model.FullRecipeModel
import com.example.myrecipes.domain.model.IngredientModel
import com.example.myrecipes.domain.model.PrepareModeModel
import com.example.myrecipes.domain.model.RecipeModel
import com.example.myrecipes.presentation.model.ItemListModel

fun RecipeModel.toEntity() = RecipeEntity(
    id = id,
    name = name,
    prepareTime = prepareTime,
)

fun RecipeEntity.toModel() = RecipeModel(
    id = id,
    name = name,
    prepareTime = prepareTime,
)

fun FullRecipeEntity.toModel() = FullRecipeModel(
    recipe = recipe.toModel(),
    ingredients = ingredients.map { it.toModel() },
    prepareModes = prepareModes.map { it.toModel() },

)

fun IngredientModel.toEntity() = IngredientEntity(
    id = id,
    name = name,
    recipeId = recipeId,
)

fun IngredientEntity.toModel() = IngredientModel(
    id = id,
    name = name,
    recipeId = recipeId,
)

fun PrepareModeModel.toEntity() = PrepareModeEntity(
    id = id,
    name = name,
    recipeId = recipeId,
)

fun PrepareModeEntity.toModel() = PrepareModeModel(
    id = id,
    name = name,
    recipeId = recipeId,
)