package com.cpp.recipebook.ui.recipe_page

sealed class RecipePageEvent {
    data class OnEditRecipeClick(val recipeId: Int): RecipePageEvent()
}