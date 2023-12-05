package com.cpp.recipebook.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cpp.recipebook.Recipe

@Composable
fun RecipeCreationPage(recipe: Recipe) {
    // Have these separate so that user can cancel changes
    val name = recipe.name
    val cuisine = recipe.cuisine
    val ingredients = recipe.ingredients
    val directions = recipe.directions
    val notes = recipe.notes

    Column(modifier = Modifier
        .padding(all = 24.dp)
        .verticalScroll(rememberScrollState())) {
        SimpleTextField(label = "Recipe Name", value = name)
        Spacer(modifier = Modifier.height(8.dp))
        SimpleTextField(label = "Cuisine", value = cuisine)
        Spacer(modifier = Modifier.height(8.dp))
        BigTextField(items = ingredients, label = "Ingredients")
        Spacer(modifier = Modifier.height(8.dp))
        BigTextField(items = directions, label = "Directions")
        Spacer(modifier = Modifier.height(8.dp))
        BigTextField(items = notes, label = "Notes")
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            onClick = { /* Handle adding image */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Image")
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

// Preview
@Preview
@Composable
fun RecipeCreationPagePreview() {
    RecipeCreationPage(recipe = Recipe(name = "Cheeseburger", cuisine = "American", ingredients = mutableListOf("Ground chuck beef", "Lettuce", "Onions"), directions = mutableListOf("Cook beef", "Add cheese"), notes = mutableListOf("Add more cheese"), image = "", id = 0))
}