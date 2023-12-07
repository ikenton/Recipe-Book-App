package com.cpp.recipebook.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.cpp.recipebook.database.RecipeRepository
import com.cpp.recipebook.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
): ViewModel() {
    var searchQuery by mutableStateOf("")
        private set
    var active by mutableStateOf(true)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnSearchQueryChange -> {
                searchQuery = event.query
                active = false
            }

            is SearchEvent.OnFocusChange -> {
                active = event.focused
            }

            is SearchEvent.OnSearchClick -> {
                // TODO: navigate to search results screen with searchQuery
            }
        }
    }
}