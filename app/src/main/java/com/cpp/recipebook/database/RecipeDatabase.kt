package com.cpp.recipebook.database

import androidx.room.Database
import androidx.room.TypeConverters
import com.cpp.recipebook.Recipe

@Database(entities = [Recipe::class], version = 1)
@TypeConverters(RecipeTypeConverters::class)
abstract class RecipeDatabase() {
    abstract fun recipeDao(): RecipeDao
}