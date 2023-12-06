package com.cpp.recipebook.ui.recipe_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cpp.recipebook.R
import com.cpp.recipebook.Recipe
import java.util.UUID

@Composable
fun RecipeListCard(
    recipe: Recipe,
    onEvent: (RecipeListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = { onEvent(RecipeListEvent.onRecipeClick(recipe.id)) },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface),
        ) {
            Image(
                painter = painterResource(id = R.drawable.hamburger),
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = recipe.name,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = recipe.cuisine,
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 16.dp, end = 16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
fun RecipeListCardPreview() {
    RecipeListCard(
        recipe = Recipe(
            id = UUID.randomUUID(),
            name = "Test Recipe",
            cuisine = "Test Cuisine",
            ingredients = "Test Ingredient 1\nTest Ingredient 2",
            directions = "Test Step 1\nTest Step 2",
            notes = "Test Notes",
            image = ""
        ),
        onEvent = {}
    )
}