package com.cpp.recipebook.ui.create_update_recipe

import androidx.lifecycle.ViewModel
import com.cpp.recipebook.database.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeCreationViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
): ViewModel() {

}