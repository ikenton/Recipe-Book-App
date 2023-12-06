package com.cpp.recipebook.database

import com.cpp.recipebook.Recipe
import java.util.UUID

interface RecipeRepository {
    // fetch all recipes
    // note: maybe should return flow in the future?
    fun getRecipes(): List<Recipe>

    //get single recipe
    suspend fun getRecipe(id: UUID): Recipe

    //get recipe name
    suspend fun getName(id: UUID):String

    //get cuisine
    suspend fun getCuisine(id: UUID):String

    // add a recipe or update a recipe
    // if id conflict, update the recipe instead
    fun addRecipe(recipe: Recipe)

    // delete a recipe
    fun deleteRecipe(vararg recipe: Recipe)
}