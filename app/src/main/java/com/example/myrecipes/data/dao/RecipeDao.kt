package com.example.myrecipes.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.myrecipes.data.entity.FullRecipeEntity
import com.example.myrecipes.data.entity.IngredientEntity
import com.example.myrecipes.data.entity.PrepareModeEntity
import com.example.myrecipes.data.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getAll() : Flow<List<RecipeEntity>>

    @Insert
    fun insert(recipe: RecipeEntity)

    @Insert
    fun insert(ingredient: IngredientEntity)

    @Insert
    fun insert(prepareMode: PrepareModeEntity)

    @Transaction
    @Query("SELECT * FROM RECIPE WHERE id = :recipeId")
    fun getFullRecipeById(recipeId: Int) : FullRecipeEntity

    @Update
    fun updateIngredient(ingredient: IngredientEntity)

    @Update
    fun updatePrepareMode(prepareMode: PrepareModeEntity)

    @Delete
    fun deleteIngredient(ingredient: IngredientEntity)

    @Delete
    fun deletePrepareMode(prepareMode: PrepareModeEntity)
}