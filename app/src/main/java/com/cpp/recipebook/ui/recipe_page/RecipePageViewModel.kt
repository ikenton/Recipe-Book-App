package com.cpp.recipebook.ui.recipe_page


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpp.recipebook.database.RecipeRepository
import com.cpp.recipebook.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RecipePageViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    savedStateHandle: SavedStateHandle,

): ViewModel() {
    //private val recipeId: Int? = savedStateHandle["recipeId"]

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var name by mutableStateOf("Name")
        private set
    var cuisine by mutableStateOf("Cuisine")
        private set
    var ingredients by mutableStateOf("")
        private set
    var directions by mutableStateOf("")
        private set
    var notes by mutableStateOf("")
        private set

    init{
        viewModelScope.launch {
            val recipeId: Int? = savedStateHandle["recipeId"]
            if (recipeId != null) {
                var recipe = recipeId.let { recipeRepository.getRecipe(it) }
                name = recipe.name
                cuisine = recipe.cuisine
                ingredients = recipe.ingredients
                directions = recipe.directions
                notes = recipe.notes
            }
        }
    }



}

