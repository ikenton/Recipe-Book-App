package com.cpp.recipebook.ui.recipe_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpp.recipebook.database.RecipeRepository
import com.cpp.recipebook.util.Routes
import com.cpp.recipebook.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
): ViewModel() {
    val recipes = recipeRepository.getRecipes()
    // will most likely have a savedstatehandle to get a search query and filter the recipes

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: RecipeListEvent) {
        when(event) {
            is RecipeListEvent.onRecipeClick -> {
                // TODO: handle navigation to recipe
                // For now we can just navigate to the creation and edit screen while detail screen is being built
                sendUiEvent(UiEvent.Navigate(Routes.CREATE_UPDATE_RECIPE + "?recipeId=${event.recipe.id}"))  // check later?
            }
            is RecipeListEvent.onAddRecipeClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.CREATE_UPDATE_RECIPE))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}