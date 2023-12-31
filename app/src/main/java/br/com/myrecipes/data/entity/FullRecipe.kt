package br.com.myrecipes.data.entity

import androidx.room.Embedded
import androidx.room.Relation

typealias FullRecipeEntity = FullRecipe

data class FullRecipe (
    @Embedded val recipe : RecipeEntity,
    @Relation(parentColumn = "id", entityColumn = "recipeId")
    val ingredients : List<IngredientEntity>,
    @Relation(parentColumn = "id", entityColumn = "recipeId")
    val prepareModes: List<PrepareModeEntity>,
)