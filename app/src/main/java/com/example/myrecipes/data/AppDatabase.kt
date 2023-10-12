package com.example.myrecipes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myrecipes.data.dao.RecipeDao
import com.example.myrecipes.data.entity.IngredientEntity
import com.example.myrecipes.data.entity.PrepareModeEntity
import com.example.myrecipes.data.entity.RecipeEntity

@Database(
    entities = [RecipeEntity::class, IngredientEntity::class, PrepareModeEntity::class],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}