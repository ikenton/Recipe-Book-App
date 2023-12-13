package com.cpp.recipebook.ui.recipe_page


import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cpp.recipebook.database.Recipe
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
    application: Application
    //,    recipeId: Int
): AndroidViewModel(application) {
    private val recipeId: Int? = savedStateHandle["recipeId"]

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
                        this@RecipePageViewModel.recipe = recipe
                    }
                    Log.d("RecipePageViewModel", "image path: $image")
                }
            }
        }
    }




}

