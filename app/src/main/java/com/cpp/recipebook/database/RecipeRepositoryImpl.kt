package com.cpp.recipebook.database

import com.cpp.recipebook.Recipe
import java.util.UUID

class RecipeRepositoryImpl(
    private val recipeDao: RecipeDao
): RecipeRepository {
    override fun getRecipes(): List<Recipe> {
        return recipeDao.getRecipes()
    }

    override suspend fun getRecipe(id: UUID): Recipe {
        return recipeDao.getRecipe(id)
    }

    override suspend fun getName(id: UUID): String {
        return recipeDao.getName(id)
    }

    override suspend fun getCuisine(id: UUID): String {
        return recipeDao.getCuisine(id)
    }

    override fun addRecipe(recipe: Recipe) {
        recipeDao.addRecipe(recipe)
    }

    override fun deleteRecipe(vararg recipe: Recipe) {
        recipeDao.deleteRecipe(*recipe)
    }
}