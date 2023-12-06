package com.cpp.recipebook.util

sealed class UiEvent {
    object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackbar(val message: String): UiEvent()
}