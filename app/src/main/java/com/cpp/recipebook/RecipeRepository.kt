package com.cpp.recipebook


import android.content.Context
import androidx.room.Room
import com.cpp.recipebook.database.RecipeDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
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
    fun getRecipes(): Flow<List<Recipe>> = database.recipeDao().getRecipes()
    suspend fun getRecipe(id: Int):Recipe=database.recipeDao().getRecipe(id)
    suspend fun getName(id: Int): String = database.recipeDao().getName(id)
    suspend fun getCuisine(id: Int): String = database.recipeDao().getCuisine(id)
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

