package com.cpp.recipebook.database

import com.cpp.recipebook.Recipe
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class RecipeRepositoryImpl(
    private val recipeDao: RecipeDao
): RecipeRepository {
    override fun getRecipes(): Flow<List<Recipe>> {
        return recipeDao.getRecipes()
    }

    override suspend fun getRecipe(id: Int): Recipe {
        return recipeDao.getRecipe(id)
    }

    override suspend fun getName(id: Int): String {
        return recipeDao.getName(id)
    }

    override suspend fun getCuisine(id: Int): String {
        return recipeDao.getCuisine(id)
    }

    override fun addRecipe(recipe: Recipe) {
        recipeDao.addRecipe(recipe)
    }

    override fun deleteRecipe(vararg recipe: Recipe) {
        recipeDao.deleteRecipe(*recipe)
    }
}