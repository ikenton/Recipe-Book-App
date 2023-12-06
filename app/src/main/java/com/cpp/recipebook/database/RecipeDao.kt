package com.cpp.recipebook.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cpp.recipebook.Recipe
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface RecipeDao {
    // fetch all recipes
    // note: maybe should return flow in the future?
    @Query("SELECT * FROM recipe")
    fun getRecipes(): Flow<List<Recipe>>

    //get single recipe
    @Query("SELECT * FROM recipe WHERE id=(:id)")
    suspend fun getRecipe(id: Int):Recipe

    //get recipe name
    @Query("SELECT name FROM recipe WHERE id=(:id)")
    suspend fun getName(id: Int):String

    //get cuisine
    @Query("SELECT cuisine FROM recipe WHERE id=(:id)")
    suspend fun getCuisine(id:Int):String

    // add a recipe or update a recipe
    // if id conflict, update the recipe instead
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecipe(recipe: Recipe)

    // delete a recipe
    @Delete
    fun deleteRecipe(vararg recipe: Recipe)

}