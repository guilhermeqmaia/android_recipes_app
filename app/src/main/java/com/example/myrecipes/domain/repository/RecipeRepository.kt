package com.example.myrecipes.domain.repository

import com.example.myrecipes.domain.model.FullRecipeModel
import com.example.myrecipes.domain.model.IngredientModel
import com.example.myrecipes.domain.model.PrepareModeModel
import com.example.myrecipes.domain.model.RecipeModel
import kotlinx.coroutines.flow.Flow


interface RecipeRepository {
    suspend fun getAll(): Flow<List<RecipeModel>>
    suspend fun insert(recipe: RecipeModel)
    suspend fun getFullRecipe(recipeId: Int) : FullRecipeModel
    suspend fun insertIngredient(ingredient: IngredientModel)
    suspend fun insertPrepareMode(prepareMode: PrepareModeModel)
    suspend fun updateIngredient(ingredient: IngredientModel)
    suspend fun updatePrepareMode(prepareMode: PrepareModeModel)
    suspend fun deleteIngredient(ingredient: IngredientModel)
    suspend fun deletePrepareMode(prepareMode: PrepareModeModel)
}