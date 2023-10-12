package com.example.myrecipes.domain.model

typealias IngredientModel = Ingredient

data class Ingredient (
    val id : Int = 0,
    val name: String,
    val recipeId : Int
)