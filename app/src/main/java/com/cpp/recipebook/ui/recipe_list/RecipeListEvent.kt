package com.cpp.recipebook.ui.recipe_list

sealed class RecipeListEvent {
    data class onRecipeClick(val recipeId: String): RecipeListEvent()
    object onAddRecipeClick: RecipeListEvent()
}
