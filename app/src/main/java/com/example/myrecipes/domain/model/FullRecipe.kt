package com.example.myrecipes.domain.model

typealias FullRecipeModel = FullRecipe

data class FullRecipe (
    val recipe : RecipeModel,
    val ingredients : List<IngredientModel>,
    val prepareModes: List<PrepareModeModel>,
)