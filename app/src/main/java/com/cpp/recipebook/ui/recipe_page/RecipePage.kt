package com.cpp.recipebook.ui.recipe_page

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.cpp.recipebook.database.Recipe

@Composable
fun RecipePage(

    //onPopBackStack: () -> Unit,
    viewModel: RecipePageViewModel = hiltViewModel(),
    recipeId: Int
){

    //tab info
    var tabIndex by remember {mutableStateOf(0)}
    val tabs = listOf("Ingredients", "Directions", "Notes")
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
            //.padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = { /*viewModel.onEvent(RecipePageEvent.onEditRecipeClick)*/
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Edit Recipe"
                )
            }
        }
    ) { values ->
        Box(
            modifier = Modifier
                .padding(values)
        ){
            Column(){
                AsyncImage(
                    model = Uri.parse(viewModel.image),
                    contentDescription = viewModel.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = viewModel.name,
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = viewModel.cuisine,
                    fontSize = 15.sp
                )
                TabRow(selectedTabIndex = tabIndex){
                    tabs.forEachIndexed{index, title ->
                        Tab(
                            text = {Text(title)},
                            selected = tabIndex == index,
                            onClick = {tabIndex = index},
                        )
                    }
                }

            }
        }
    }
}

