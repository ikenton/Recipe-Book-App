package com.cpp.recipebook.ui.recipe_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpp.recipebook.database.RecipeRepository
import com.cpp.recipebook.util.Routes
import com.cpp.recipebook.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val recipes = recipeRepository.getRecipes()
    var filteredRecipes = recipes

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    // TODO: Make this search less strict
    init {
        val query = savedStateHandle.get<String>("query")
        if (query != null) {
            filteredRecipes = filteredRecipes.map { list ->
                list.filter { recipe ->
                    recipe.name.contains(query, ignoreCase = true) ||
                    recipe.cuisine.contains(query, ignoreCase = true) ||
                    recipe.ingredients.contains(query, ignoreCase = true) ||
                    recipe.directions.contains(query, ignoreCase = true) ||
                    recipe.notes.contains(query, ignoreCase = true)
                }
            }
        }
    }

    fun onEvent(event: RecipeListEvent) {
        when(event) {
            is RecipeListEvent.onRecipeClick -> {
                // TODO: handle navigation to recipe
                // For now we can just navigate to the creation and edit screen while detail screen is being built
                sendUiEvent(UiEvent.Navigate(Routes.RECIPE_PAGE + "?recipeId=${event.recipeId}"))  // check later?
            }
            is RecipeListEvent.onAddRecipeClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.CREATE_UPDATE_RECIPE))
            }
            is RecipeListEvent.OnDrawerNavClick -> {
                sendUiEvent(UiEvent.Navigate(event.route))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}