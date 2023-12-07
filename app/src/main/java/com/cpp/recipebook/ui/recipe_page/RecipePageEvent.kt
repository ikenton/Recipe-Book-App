package com.cpp.recipebook.ui.recipe_page

import com.cpp.recipebook.database.Recipe

sealed class RecipePageEvent {
    data class onEditRecipeClick(val recipe: Recipe): RecipePageEvent()
}