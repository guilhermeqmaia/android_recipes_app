package br.com.myrecipes.data.repository

import br.com.myrecipes.data.dao.RecipeDao
import br.com.myrecipes.data.entity.IngredientEntity
import br.com.myrecipes.data.mapper.toEntity
import br.com.myrecipes.data.mapper.toModel
import br.com.myrecipes.domain.model.FullRecipeModel
import br.com.myrecipes.domain.model.IngredientModel
import br.com.myrecipes.domain.model.PrepareModeModel
import br.com.myrecipes.domain.model.RecipeModel
import br.com.myrecipes.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import kotlinx.coroutines.withContext

class RecipeRepositoryImpl(
    private val dao : RecipeDao,
) : RecipeRepository {
    override suspend fun getAll(): Flow<List<RecipeModel>> = withContext(Dispatchers.IO) {
        dao.getAll().map { list ->
            list.map { it.toModel() }
        }
    }

    override suspend fun insert(recipe: RecipeModel) = withContext(Dispatchers.IO) {
        dao.insert(recipe.toEntity())
    }

    override suspend fun getFullRecipe(recipeId: Int): FullRecipeModel = withContext(Dispatchers.IO) {
        dao.getFullRecipeById(recipeId).toModel()
    }

    override suspend fun insertIngredient(ingredient: IngredientModel) =
        withContext(Dispatchers.IO) {
            dao.insert(
                IngredientEntity(
                    id = ingredient.id,
                    name = ingredient.name,
                    recipeId = ingredient.recipeId
                )
            )
        }

    override suspend fun insertPrepareMode(prepareMode: PrepareModeModel) =
        withContext(Dispatchers.IO) {
            dao.insert(prepareMode.toEntity())
        }

    override suspend fun updateIngredient(ingredient: IngredientModel) =
        withContext(Dispatchers.IO) {
            dao.updateIngredient(ingredient.toEntity())
        }

    override suspend fun updatePrepareMode(prepareMode: PrepareModeModel) =
        withContext(Dispatchers.IO) {
            dao.updatePrepareMode(prepareMode.toEntity())
        }

    override suspend fun deleteIngredient(ingredient: IngredientModel) =
        withContext(Dispatchers.IO) {
            dao.deleteIngredient(ingredient.toEntity())
        }

    override suspend fun deletePrepareMode(prepareMode: PrepareModeModel) =
        withContext(Dispatchers.IO) {
            dao.deletePrepareMode(prepareMode.toEntity())
        }
}