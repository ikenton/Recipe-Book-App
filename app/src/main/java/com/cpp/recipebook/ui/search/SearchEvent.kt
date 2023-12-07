package com.cpp.recipebook.ui.search

sealed class SearchEvent {
    data class OnSearchQueryChange(val query: String): SearchEvent()
    data class OnFocusChange(val focused: Boolean): SearchEvent()
    data class OnSearchClick(val query: String): SearchEvent()
}
