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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cpp.recipebook.util.UiEvent

@Composable
fun SearchScreen (
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = true) {
        focusRequester.requestFocus()
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

    Scaffold() { values ->
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
                IconButton(onClick = {
                    if (viewModel.searchQuery.isNotEmpty()) {
                        viewModel.onEvent(SearchEvent.OnSearchQueryChange(""))
                    } else {
                        viewModel.onEvent(SearchEvent.OnFocusChange(false))
                    }
                }) {
                  Icon(
                      imageVector = Icons.Filled.Clear,
                        contentDescription = "Clear"
                  )
                }
            },
            modifier = Modifier
                .padding(values)
                .fillMaxWidth()
                .focusRequester(focusRequester)
        ) {
            // TODO: Search results
        }
    }
}