package com.example.myreceipts.domain.model

typealias RecipeModel = Recipe

data class Recipe(
    val id: Int,
    val name: String,
)