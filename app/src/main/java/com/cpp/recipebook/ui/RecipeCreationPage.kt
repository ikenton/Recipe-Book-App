package com.cpp.recipebook.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RecipeCreationPage() {
    Column {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Recipe Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Cuisine") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            onClick = { /* Handle adding image */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Image")
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Dynamic list of ingredients
        Text(
            text = "Ingredients",
            style = MaterialTheme.typography.headlineSmall
        )
        // TODO: Add dynamic list of ingredients
        Spacer(modifier = Modifier.height(8.dp))
        // Dynamic list of directions
        Text(
            text = "Directions",
            style = MaterialTheme.typography.headlineSmall
        )
        // TODO: Add dynamic list of directions
        Spacer(modifier = Modifier.height(8.dp))
        // Dynamic list of notes
        Text(
            text = "Notes",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

// Preview
@Preview
@Composable
fun RecipeCreationPagePreview() {
    RecipeCreationPage()
}