package com.cpp.recipebook


import android.content.Context
import androidx.room.Room
import com.cpp.recipebook.database.RecipeDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID

private const val DATABASE_NAME = "recipe-database"
class RecipeRepository private constructor(
    context: Context,
    private val coroutineScope: CoroutineScope = GlobalScope
) {
    private val database: RecipeDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            RecipeDatabase::class.java,
            DATABASE_NAME
        )
        //.addMigrations()
        .build()
    fun getRecipes(): List<Recipe> = database.recipeDao().getRecipes()
    suspend fun getRecipe(id: UUID):Recipe=database.recipeDao().getRecipe(id)

    fun updateRecipe(recipe: Recipe){
        coroutineScope.launch {
            database.recipeDao().updateRecipe(recipe)
        }
    }
    suspend fun addRecipe(recipe: Recipe) {
        database.recipeDao().addRecipe(recipe )
    }
    suspend fun deleteRecipe(recipe: Recipe){
        database.recipeDao().deleteRecipe(recipe )
    }
    companion object{
        private var INSTANCE: RecipeRepository? = null
        fun initialize(
            context: Context){
            if(INSTANCE == null){
                INSTANCE = RecipeRepository(context)
            }
        }

        fun get(): RecipeRepository{
            return INSTANCE ?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}
