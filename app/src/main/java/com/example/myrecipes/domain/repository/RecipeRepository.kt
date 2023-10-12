package com.example.myrecipes.domain.repository

import com.example.myrecipes.domain.model.RecipeModel


interface RecipeRepository {
    suspend fun getAll() : List<RecipeModel>
    suspend fun insert(recipe: RecipeModel)
}