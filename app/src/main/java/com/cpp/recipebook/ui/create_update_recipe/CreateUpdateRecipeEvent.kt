package com.cpp.recipebook.ui.create_update_recipe

sealed class CreateUpdateRecipeEvent {
    data class OnNameChange(val name: String): CreateUpdateRecipeEvent()
    data class OnCuisineChange(val cuisine: String): CreateUpdateRecipeEvent()
    data class OnIngredientsChange(val ingredients: String): CreateUpdateRecipeEvent()
    data class OnDirectionsChange(val directions: String): CreateUpdateRecipeEvent()
    data class OnNotesChange(val notes: String): CreateUpdateRecipeEvent()
    object OnSaveClick: CreateUpdateRecipeEvent()
}