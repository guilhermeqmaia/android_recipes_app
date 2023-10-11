package com.example.myreceipts.data.repository

import com.example.myreceipts.data.dao.RecipeDao
import com.example.myreceipts.data.mapper.toEntity
import com.example.myreceipts.data.mapper.toModel
import com.example.myreceipts.domain.model.RecipeModel
import com.example.myreceipts.domain.repository.RecipeRepository
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