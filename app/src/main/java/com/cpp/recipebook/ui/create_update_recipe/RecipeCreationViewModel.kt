package com.cpp.recipebook.ui.create_update_recipe

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.cpp.recipebook.Recipe
import com.cpp.recipebook.database.RecipeRepository
import com.cpp.recipebook.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

@HiltViewModel
class RecipeCreationViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    var recipe = mutableStateOf<Recipe?>(null)
        private set
    var name = mutableStateOf("")
        private set
    var cuisine = mutableStateOf("")
        private set
    var ingredients = mutableStateOf("")
        private set
    var instructions = mutableStateOf("")
        private set
    var notes = mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()   // 1:10:38
    val uiEvent = _uiEvent

//    figure out image later lol
}