package com.example.myrecipes.data.repository

import com.example.myrecipes.data.dao.RecipeDao
import com.example.myrecipes.data.mapper.toEntity
import com.example.myrecipes.data.mapper.toModel
import com.example.myrecipes.domain.model.RecipeModel
import com.example.myrecipes.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepositoryImpl(
    private val dao : RecipeDao,
) : RecipeRepository {
    override suspend fun getAll(): List<RecipeModel> = withContext(Dispatchers.IO) {
        dao.getAll().map { it.toModel() }
    }

    override suspend fun insert(recipe: RecipeModel) = withContext(Dispatchers.IO) {
        dao.insert(recipe.toEntity())
    }
}