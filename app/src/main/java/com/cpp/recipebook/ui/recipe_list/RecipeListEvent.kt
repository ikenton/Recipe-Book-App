package com.cpp.recipebook.ui.recipe_list

import com.cpp.recipebook.database.Recipe
import java.util.UUID

sealed class RecipeListEvent {
    data class onRecipeClick(val recipe: Recipe): RecipeListEvent()
    data class OnDrawerNavClick(val route: String): RecipeListEvent()
    object onAddRecipeClick: RecipeListEvent()
}
