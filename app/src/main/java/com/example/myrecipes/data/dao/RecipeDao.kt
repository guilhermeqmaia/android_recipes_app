package com.example.myrecipes.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.myrecipes.data.entity.FullRecipeEntity
import com.example.myrecipes.data.entity.RecipeEntity


@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getAll() : List<RecipeEntity>

    @Insert
    fun insert(recipe: RecipeEntity)

    @Transaction
    @Query("SELECT * FROM RECIPE WHERE id = :recipeId")
    fun getFullRecipeById(recipeId: Int) : FullRecipeEntity
}