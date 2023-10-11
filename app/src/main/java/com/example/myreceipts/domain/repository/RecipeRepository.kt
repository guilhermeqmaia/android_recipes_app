package com.example.myreceipts.domain.repository

import com.example.myreceipts.domain.model.RecipeModel


interface RecipeRepository {
    suspend fun getAll() : List<RecipeModel>
    suspend fun insert(recipe: RecipeModel)
}