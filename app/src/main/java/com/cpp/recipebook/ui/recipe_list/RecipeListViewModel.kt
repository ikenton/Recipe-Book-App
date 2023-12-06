package com.cpp.recipebook.ui.recipe_list

import androidx.lifecycle.ViewModel
import com.cpp.recipebook.database.RecipeRepository
import javax.inject.Inject

class RecipeListViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
): ViewModel() {
    val recipes = recipeRepository.getRecipes()
}