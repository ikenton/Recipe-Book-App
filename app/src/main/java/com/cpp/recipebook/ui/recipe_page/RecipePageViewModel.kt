package com.cpp.recipebook.ui.recipe_page


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
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

): ViewModel() {
    //private val recipeId: Int? = savedStateHandle["recipeId"]

    private lateinit var recipe: Recipe
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    /*private var recipe by mutableStateOf<Recipe?>(null)
    var name by mutableStateOf("")
        private set
    var cuisine by mutableStateOf("")
        private set
    var ingredients by mutableStateOf("")
        private set
    var directions by mutableStateOf("")
        private set
    var notes by mutableStateOf("")
        private set*/
    init{
        val recipeId = savedStateHandle.get<Int>("?recipeId")
        viewModelScope.launch {
            //var recipe = recipeId?.let { recipeRepository.getRecipe(it) }
            if (recipeId != null) {
                recipeRepository.getRecipe(recipeId).let { recipe ->
                    var name = recipe.name
                    var cuisine = recipe.cuisine
                    var ingredients = recipe.ingredients
                    var directions = recipe.directions
                    var notes = recipe.notes
                    this@RecipePageViewModel.recipe = recipe
                }
            }
        }
    }
}
