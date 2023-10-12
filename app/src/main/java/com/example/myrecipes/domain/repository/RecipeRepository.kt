package com.example.myrecipes.domain.repository

import com.example.myrecipes.domain.model.FullRecipeModel
import com.example.myrecipes.domain.model.RecipeModel


interface RecipeRepository {
    suspend fun getAll() : List<RecipeModel>
    suspend fun insert(recipe: RecipeModel)
    suspend fun getFullRecipe(recipeId: Int) : FullRecipeModel
}