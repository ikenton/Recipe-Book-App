package com.cpp.recipebook.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cpp.recipebook.Recipe

@Database(entities = [Recipe::class], version = 1)
@TypeConverters(RecipeTypeConverters::class)
abstract class RecipeDatabase() : RoomDatabase(){
    abstract fun recipeDao(): RecipeDao
}