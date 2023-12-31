package br.com.myrecipes.domain.model

typealias RecipeModel = Recipe

data class Recipe(
    val id: Int = 0,
    val name: String,
    val prepareTime : String,
)