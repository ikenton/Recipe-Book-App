package com.cpp.recipebook.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RecipeCreationPage() {
    Column {
        TextField(value = "", onValueChange = {}, label = { Text("Recipe Name") })

    }
}

// Preview
@Preview
@Composable
fun RecipeCreationPagePreview() {
    RecipeCreationPage()
}