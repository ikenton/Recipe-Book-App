package com.cpp.recipebook.ui.recipe_list

import java.util.UUID

sealed class RecipeListEvent {
    data class onRecipeClick(val recipeId: Int): RecipeListEvent()
    object onAddRecipeClick: RecipeListEvent()
}
