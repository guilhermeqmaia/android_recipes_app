package com.example.myrecipes.data

import android.content.Context
import androidx.room.Room

val Context.db : AppDatabase
    get() = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "recipe_db"
    ).build()