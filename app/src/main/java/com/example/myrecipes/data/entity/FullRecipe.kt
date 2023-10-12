package com.example.myrecipes.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class FullRecipe (
    @Embedded val recipe : RecipeEntity,
    @Relation(parentColumn = "id", entityColumn = "recipeId")
    val ingredients : List<IngredientEntity>,
    @Relation(parentColumn = "id", entityColumn = "recipeId")
    val prepareModes: List<PrepareModeEntity>,
)