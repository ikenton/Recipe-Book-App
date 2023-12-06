package com.cpp.recipebook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class RecipeViewModel(recipeId: UUID) : ViewModel() {
    private val recipeRepository = RecipeRepository.get()
    private val _recipe: MutableStateFlow<Recipe?> = MutableStateFlow(null)
    val recipe: StateFlow<Recipe?> = _recipe.asStateFlow()

    init{
        viewModelScope.launch {
            _recipe.value = recipeRepository.getRecipe(recipeId)
        }
    }

    fun updateRecipe(onUpdate: (Recipe) -> Recipe){
        _recipe.update { oldRecipe ->
            oldRecipe?.let{ onUpdate(it) }
        }
    }

    override fun onCleared() {
        super.onCleared()
//        recipe.value?.let{recipeRepository.addRecipe(it)}
//        commenting out for now so i can build LOL
    }

    suspend fun removeRecipe(recipeId: UUID){
        recipeRepository.getRecipe(recipeId)
    }
}

class RecipeViewModelFactory(
    private val recipeId: UUID
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return RecipeViewModel(recipeId) as T
    }
}