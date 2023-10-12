package br.com.myrecipes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.myrecipes.data.dao.RecipeDao
import br.com.myrecipes.data.entity.IngredientEntity
import br.com.myrecipes.data.entity.PrepareModeEntity
import br.com.myrecipes.data.entity.RecipeEntity


@Database(
    entities = [RecipeEntity::class, IngredientEntity::class, PrepareModeEntity::class],
    version = 2,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}
