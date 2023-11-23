package com.cpp.recipebook.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cpp.recipebook.Recipe

@Dao
interface RecipeDao {
    // fetch all recipes
    @Query("SELECT * FROM recipe")
    fun getRecipes(): List<Recipe>

    // add a recipe
    @Insert
    fun addRecipe(recipe: Recipe)

    // delete a recipe
    @Query("DELETE FROM recipe WHERE id=(:id)")
    fun deleteRecipe(id: Int)

    // update a recipe
    @Update
    fun updateRecipe(recipe: Recipe)
}