package com.cpp.recipebook.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cpp.recipebook.Recipe
import java.util.UUID

@Dao
interface RecipeDao {
    // fetch all recipes
    @Query("SELECT * FROM recipe")
    fun getRecipes(): List<Recipe>

    //get single recipe
    @Query("SELECT * FROM recipe WHERE id=(:id)")
    suspend fun getRecipe(id: UUID):Recipe

    //get recipe name
    @Query("SELECT name FROM recipe WHERE id=(:id)")
    suspend fun getName(id: UUID):String

    //get cuisine
    @Query("SELECT cuisine FROM recipe WHERE id=(:id)")
    suspend fun getCuisine(id:UUID):String

    // add a recipe
    @Insert
    fun addRecipe(recipe: Recipe)

    // delete a recipe
    @Delete
    fun deleteRecipe(vararg recipe: Recipe)

    // update a recipe
    @Update
    fun updateRecipe(recipe: Recipe)
}