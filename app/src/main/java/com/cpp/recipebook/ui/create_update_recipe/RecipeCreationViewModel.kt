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
    var instructions by mutableStateOf("")
        private set
    var notes by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
//    figure out image later lol

    init {
        val recipeId = savedStateHandle.get<UUID>("recipeId")
        if(recipeId != null) {
            viewModelScope.launch {
                recipe = recipeRepository.getRecipe(recipeId)
            }
        }
    }
}