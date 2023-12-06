package com.cpp.recipebook.ui.create_update_recipe

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cpp.recipebook.util.UiEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")    // TODO: remove this
@Composable
fun CreateUpdateRecipeScreen(
    viewModel: RecipeCreationViewModel = hiltViewModel(), // 1:19
    onPopBackStack: () -> Unit
) {
    // TODO: figure out modern way to get snackbar on scaffold
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.ShowSnackbar -> {
                    // TODO: show snackbar
                }

                is UiEvent.Navigate -> {
                    // TODO: navigate
                }

                is UiEvent.PopBackStack -> onPopBackStack()
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(CreateUpdateRecipeEvent.OnSaveClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Save Recipe"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // name
            TextField(
                value = viewModel.name,
                onValueChange = {
                    viewModel.onEvent(CreateUpdateRecipeEvent.OnNameChange(it))
                },
                label = { Text("Name") },
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // cuisine
            TextField(
                value = viewModel.cuisine,
                onValueChange = {
                    viewModel.onEvent(CreateUpdateRecipeEvent.OnCuisineChange(it))
                },
                label = { Text("Cuisine") },
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // ingredients
            TextField(
                value = viewModel.ingredients,
                onValueChange = {
                    viewModel.onEvent(CreateUpdateRecipeEvent.OnIngredientsChange(it))
                },
                label = { Text("Ingredients") },
                modifier = Modifier.fillMaxSize()

            )
            Spacer(modifier = Modifier.height(8.dp))

            // directions
            TextField(
                value = viewModel.directions,
                onValueChange = {
                    viewModel.onEvent(CreateUpdateRecipeEvent.OnDirectionsChange(it))
                },
                label = { Text("Directions") },
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // notes
            TextField(
                value = viewModel.notes,
                onValueChange = {
                    viewModel.onEvent(CreateUpdateRecipeEvent.OnNotesChange(it))
                },
                label = { Text("Notes") },
                modifier = Modifier.fillMaxSize()
            )

            // TODO: image
        }
    }
}