package com.cpp.recipebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cpp.recipebook.ui.RecipeCreationPage
import com.cpp.recipebook.ui.theme.RecipeBookTheme

class TestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeCreationPage(recipe = Recipe(name = "Cheeseburger", cuisine = "American", ingredients = mutableListOf("Ground chuck beef", "Lettuce", "Onions"), directions = mutableListOf("Cook beef", "Add cheese"), notes = mutableListOf("Add more cheese"), image = "", id = 0))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecipeBookTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RecipeCreationPage(recipe = Recipe(name = "Cheeseburger", cuisine = "American", ingredients = mutableListOf("Ground chuck beef", "Lettuce", "Onions"), directions = mutableListOf("Cook beef", "Add cheese"), notes = mutableListOf("Add more cheese"), image = "", id = 0))
        }
    }
}