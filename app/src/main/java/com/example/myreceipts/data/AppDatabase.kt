package com.example.myreceipts.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myreceipts.data.dao.RecipeDao
import com.example.myreceipts.data.entity.Recipe
import com.example.myreceipts.data.entity.RecipeEntity

@Database(
    entities = [RecipeEntity::class],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}