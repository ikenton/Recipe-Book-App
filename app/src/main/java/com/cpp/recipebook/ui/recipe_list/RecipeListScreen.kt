package com.cpp.recipebook.ui.recipe_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.cpp.recipebook.util.UiEvent

@Composable
fun RecipeListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: RecipeListViewModel = hiltViewModel(),
) {
    val recipes = viewModel.recipes.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackbar -> TODO()
                else -> Unit
            }
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(RecipeListEvent.onAddRecipeClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Recipe"
                )
            }
        }
    ) { values ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(values)
        ) {
            // fill with rows, 2 per row
            items(recipes.value.size / 2) { index ->
                Row {
                    RecipeListCard(
                        recipe = recipes.value[index * 2],
                        onEvent = viewModel::onEvent,
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                viewModel.onEvent(RecipeListEvent.onRecipeClick(recipes.value[index * 2].id!!))
                            }
                    )
                    RecipeListCard(
                        recipe = recipes.value[index * 2 + 1],
                        onEvent = viewModel::onEvent,
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                viewModel.onEvent(RecipeListEvent.onRecipeClick(recipes.value[index * 2 + 1].id!!))
                            }
                    )
                }
            }
        }
    }
}