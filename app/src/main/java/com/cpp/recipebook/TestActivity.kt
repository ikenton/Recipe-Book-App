@file:OptIn(ExperimentalMaterial3Api::class)

package com.cpp.recipebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreationPreview() {
    RecipeBookTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    TopAppBar(
                        title = { Text("Recipe Creation") },
                        navigationIcon = {
                            IconButton(onClick = { /*TODO: handle popping back*/ }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Go back"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { /*TODO: handle saving*/ }) {
                                Icon(
                                    imageVector = Icons.Default.Done,
                                    contentDescription = "Save"
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior
                    )
                }
            ) { values ->
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(values)
                ){
                    RecipeCreationPage(
                        recipe = Recipe(
                            name = "Cheeseburger",
                            cuisine = "American",
                            ingredients = "Ground chuck beef\nLettuce\nOnions",
                            directions ="Cook beef\nAdd cheese",
                            notes = "Add more cheese",
                            image = "",
                            id = 0
                        )
                    )
                }


            }
        }
    }
}