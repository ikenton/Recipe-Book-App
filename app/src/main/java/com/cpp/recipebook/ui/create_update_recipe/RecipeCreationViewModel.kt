package com.cpp.recipebook.ui.create_update_recipe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpp.recipebook.Recipe
import com.cpp.recipebook.database.RecipeRepository
import com.cpp.recipebook.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RecipeCreationViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    var recipe by mutableStateOf<Recipe?>(null)
        private set
    var name by mutableStateOf("")
        private set
    var cuisine by mutableStateOf("")
        private set
    var ingredients by mutableStateOf("")
        private set
    var directions by mutableStateOf("")
        private set
    var notes by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
//    figure out image later lol

    init {
        val recipeId = savedStateHandle.get<Int>("recipeId")
        if(recipeId != null) {
            viewModelScope.launch {
                recipe = recipeRepository.getRecipe(recipeId).let { recipe ->
                    name = recipe.name
                    cuisine = recipe.cuisine
                    ingredients = recipe.ingredients
                    directions = recipe.directions
                    notes = recipe.notes
                    recipe
                }
            }
        }
    }

    fun onEvent(event: CreateUpdateRecipeEvent) {
        when(event) {
            is CreateUpdateRecipeEvent.OnNameChange -> {
                name = event.name
            }
            is CreateUpdateRecipeEvent.OnCuisineChange -> {
                cuisine = event.cuisine
            }
            is CreateUpdateRecipeEvent.OnIngredientsChange -> {
                ingredients = event.ingredients
            }
            is CreateUpdateRecipeEvent.OnDirectionsChange -> {
                directions = event.directions
            }
            is CreateUpdateRecipeEvent.OnNotesChange -> {
                notes = event.notes
            }
            is CreateUpdateRecipeEvent.OnSaveClick -> {
                viewModelScope.launch {
                    if (name.isBlank() || cuisine.isBlank() || ingredients.isBlank() || directions.isBlank() || notes.isBlank()) {
                        sendUiEvent(UiEvent.ShowSnackbar("Please fill out all fields"))
                        return@launch
                    }
                    recipeRepository.addRecipe(
                        Recipe(
                            name = name,
                            cuisine = cuisine,
                            ingredients = ingredients,
                            directions = directions,
                            notes = notes,
                            image = ""  // TEMP: figure out image later lol
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}