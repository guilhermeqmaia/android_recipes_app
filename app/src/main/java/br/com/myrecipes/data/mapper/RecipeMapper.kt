package br.com.myrecipes.data.mapper

import br.com.myrecipes.data.entity.FullRecipeEntity
import br.com.myrecipes.data.entity.IngredientEntity
import br.com.myrecipes.data.entity.PrepareModeEntity
import br.com.myrecipes.data.entity.RecipeEntity
import br.com.myrecipes.domain.model.FullRecipeModel
import br.com.myrecipes.domain.model.IngredientModel
import br.com.myrecipes.domain.model.PrepareModeModel
import br.com.myrecipes.domain.model.RecipeModel

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