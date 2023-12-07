@file:OptIn(ExperimentalMaterial3Api::class)

package com.cpp.recipebook.ui.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cpp.recipebook.util.UiEvent

@Composable
fun SearchScreen (
    viewModel: SearchViewModel,
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.ShowSnackbar -> {
                    // do nothing
                }
                is UiEvent.Navigate -> {
                    onNavigate(uiEvent)
                }
                is UiEvent.PopBackStack -> onPopBackStack()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text="Search") },
                navigationIcon = {
                    IconButton(onClick = { onPopBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { values ->
        SearchBar(
            query = viewModel.searchQuery,
            onQueryChange = {
                viewModel.onEvent(SearchEvent.OnSearchQueryChange(it))
            },
            onSearch = {
                viewModel.onEvent(SearchEvent.OnSearchClick)
            },
            active = viewModel.active ,
            onActiveChange = {
                viewModel.onEvent(SearchEvent.OnFocusChange(it))
            },
            placeholder = { Text(text = "Search") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            },
            trailingIcon = {
                if (viewModel.searchQuery.isNotEmpty()) {
                    IconButton(onClick = {
                        viewModel.onEvent(SearchEvent.OnSearchQueryChange(""))
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "Clear"
                        )
                    }
                } else {
                    viewModel.onEvent(SearchEvent.OnFocusChange(false))
                }
            },
            modifier = Modifier
                .padding(values)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // TODO: Search results
        }
    }
}