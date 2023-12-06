package com.cpp.recipebook.ui.recipe_list

import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cpp.recipebook.Recipe

@Composable
fun RecipeListCard(
    recipe: Recipe,
    onEvent: (RecipeListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = { onEvent(RecipeListEvent.onRecipeClick(recipe.id)) },
        modifier = modifier.width(200.dp)
    ) {

    }
}