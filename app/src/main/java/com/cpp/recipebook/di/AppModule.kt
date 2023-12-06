package com.cpp.recipebook.di

import android.app.Application
import androidx.room.Room
import com.cpp.recipebook.database.RecipeDatabase
import com.cpp.recipebook.database.RecipeRepository
import com.cpp.recipebook.database.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRecipeDatabase(app: Application): RecipeDatabase {
        return Room.databaseBuilder(
            app,
            RecipeDatabase::class.java,
            "recipe_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(db: RecipeDatabase): RecipeRepository {
        return RecipeRepositoryImpl(db.recipeDao()) // check on this later
    }
}