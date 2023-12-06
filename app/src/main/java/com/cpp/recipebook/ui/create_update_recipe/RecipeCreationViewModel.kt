package com.cpp.recipebook.ui.create_update_recipe

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.compose.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cpp.recipebook.database.Recipe
import com.cpp.recipebook.database.RecipeRepository
import com.cpp.recipebook.util.UiEvent
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecipeCreationViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    savedStateHandle: SavedStateHandle,
    application: Application
): AndroidViewModel(application) {
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
    var image by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
//    figure out image later lol

    init {
        val recipeId = savedStateHandle.get<Int>("recipeId")
        if (recipeId != -1) {
            viewModelScope.launch {
                if (recipeId != null) {
                    recipeRepository.getRecipe(recipeId).let { recipe ->
                        name = recipe.name
                        cuisine = recipe.cuisine
                        ingredients = recipe.ingredients
                        directions = recipe.directions
                        notes = recipe.notes
                        image = recipe.image
                        this@RecipeCreationViewModel.recipe = recipe
                    }
                    Log.d("RecipeCreationViewModel", "image path: $image")
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
            is CreateUpdateRecipeEvent.OnImageChange -> {
                viewModelScope.launch {
                    val context = getApplication<Application>().applicationContext
                    val inputStream = context.contentResolver.openInputStream(event.uri!!)
                    val filename = "img_${System.currentTimeMillis()}.jpg"
                    val outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE)
                    inputStream?.copyTo(outputStream)
                    image = context.getFileStreamPath(filename).absolutePath
                }
            }
            is CreateUpdateRecipeEvent.OnSaveClick -> {
                viewModelScope.launch {
                    if (name.isBlank() || cuisine.isBlank()) {
                        sendUiEvent(UiEvent.ShowSnackbar("Please fill out all fields"))
                        return@launch
                    }
                    withContext(Dispatchers.IO) {
                        recipeRepository.addRecipe(
                            Recipe(
                                id = recipe?.id,
                                name = name,
                                cuisine = cuisine,
                                ingredients = ingredients,
                                directions = directions,
                                notes = notes,
                                image = image
                            )
                        )
                    }
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