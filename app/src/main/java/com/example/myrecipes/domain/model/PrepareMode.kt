package com.example.myrecipes.domain.model

typealias PrepareModeModel = PrepareMode

data class PrepareMode (
    val id : Int = 0,
    val name: String,
    val recipeId : Int
)