package com.cpp.recipebook.ui.recipe_list

import com.cpp.recipebook.database.Recipe
import java.util.UUID

sealed class RecipeListEvent {
    data class onRecipeClick(val recipe: Recipe): RecipeListEvent()
    object onAddRecipeClick: RecipeListEvent()
}
