package com.cpp.recipebook

import android.app.Application
import androidx.room.Room
import com.cpp.recipebook.database.RecipeDatabase

@HiltAndroidApp
class RecipeApplication : Application() {
    private val database: RecipeDatabase by lazy{
        Room.databaseBuilder(
            this,
            RecipeDatabase::class.java, "recipe_database"
        ).build()
    }
    fun insertRecipe(recipe: Recipe){
        val dao = database.recipeDao()
        dao.addRecipe(recipe)
    }
}