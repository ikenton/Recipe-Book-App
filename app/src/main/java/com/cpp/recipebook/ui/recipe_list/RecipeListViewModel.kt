package com.cpp.recipebook.ui.recipe_list

import androidx.lifecycle.ViewModel
import com.cpp.recipebook.database.RecipeRepository
import com.cpp.recipebook.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class RecipeListViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
): ViewModel() {
    val recipes = recipeRepository.getRecipes()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: RecipeListEvent) {
        when(event) {
            is RecipeListEvent.onRecipeClick -> {
                // TODO: handle navigation to recipe
                // For now we can just navigate to the creation and edit screen while detail screen is being built
            }
            is RecipeListEvent.onAddRecipeClick -> {
                // TODO: handle navigation to recipe creation screen
            }
        }
    }
}