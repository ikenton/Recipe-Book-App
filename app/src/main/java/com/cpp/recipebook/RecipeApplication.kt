package com.cpp.recipebook

import android.app.Application
import androidx.room.Room
import com.cpp.recipebook.database.RecipeDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RecipeApplication : Application() {

}