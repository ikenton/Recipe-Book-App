@file:OptIn(ExperimentalMaterial3Api::class)

package com.cpp.recipebook.ui.create_update_recipe

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.cpp.recipebook.util.UiEvent

@Composable
fun CreateUpdateRecipeScreen(
    viewModel: RecipeCreationViewModel = hiltViewModel(), // 1:19
    onPopBackStack: () -> Unit,
    recipeId: Int?
) {
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> viewModel.onEvent(CreateUpdateRecipeEvent.OnImageChange(uri))}
    )
    val snackbarHostState = remember { SnackbarHostState() }

    // TODO: figure out modern way to get snackbar on scaffold
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(uiEvent.message)
                }

                is UiEvent.Navigate -> {
                    // do nothing
                }

                is UiEvent.PopBackStack -> onPopBackStack()
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
            )
        },
        topBar = {
                 TopAppBar(
                     title = { Text(text = "Add/Edit Recipe") },
                     navigationIcon = {
                         IconButton(onClick = { onPopBackStack() }) {
                             Icon(
                                 imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                 contentDescription = "Back"
                             )
                         }
                     },
                     actions = {
                         IconButton(onClick = {
                             viewModel.onEvent(CreateUpdateRecipeEvent.OnSaveClick)
                         }) {
                             Icon(
                                 imageVector = Icons.Default.Done,
                                 contentDescription = "Save Recipe"
                             )
                         }
                     }
                 )
        },
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
    ) { values ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(values)
                .padding(16.dp)
        ) {
            // name
            TextField(
                value = viewModel.name,
                onValueChange = {
                    viewModel.onEvent(CreateUpdateRecipeEvent.OnNameChange(it))
                },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // cuisine
            TextField(
                value = viewModel.cuisine,
                onValueChange = {
                    viewModel.onEvent(CreateUpdateRecipeEvent.OnCuisineChange(it))
                },
                label = { Text("Cuisine") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // ingredients
            TextField(
                value = viewModel.ingredients,
                onValueChange = {
                    viewModel.onEvent(CreateUpdateRecipeEvent.OnIngredientsChange(it))
                },
                label = { Text("Ingredients") },
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(modifier = Modifier.height(8.dp))

            // directions
            TextField(
                value = viewModel.directions,
                onValueChange = {
                    viewModel.onEvent(CreateUpdateRecipeEvent.OnDirectionsChange(it))
                },
                label = { Text("Directions") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // notes
            TextField(
                value = viewModel.notes,
                onValueChange = {
                    viewModel.onEvent(CreateUpdateRecipeEvent.OnNotesChange(it))
                },
                label = { Text("Notes") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                singlePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Image")
            }
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = Uri.parse(viewModel.image),
                contentDescription = "image of food",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(shape = MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
        }
    }
}