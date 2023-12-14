package com.cpp.recipebook.ui.recipe_page

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun RecipePage(

    //onPopBackStack: () -> Unit,
    viewModel: RecipePageViewModel = hiltViewModel(),
    recipeId: Int,
    navController: NavController,
    onPopBackStack: () -> Unit
){

    //tab info
    var tabIndex by remember {mutableStateOf(0)}
    val tabs = listOf("Ingredients", "Directions", "Notes")
    var isLandscape by remember{ mutableStateOf(false)}
    val configuration = LocalConfiguration.current
    isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val listState = rememberLazyListState()
    val recipe = viewModel.recipe
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(RecipePageEvent.OnEditRecipeClick(recipeId), navController)
            }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Recipe"
                )
            }
        }
    )
    { values ->
        if(isLandscape){
            LazyColumn {
                item {
                    AsyncImage(
                        model = Uri.parse(viewModel.image),
                        contentDescription = viewModel.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                item{
                    Text(
                        text = viewModel.name,
                        style = TextStyle(
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(start = 10.dp, top = 10.dp)
                    )
                }
                item{
                    Text(
                        text = viewModel.cuisine,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(start = 10.dp)
                    )
                }
                item{
                    TabRow(selectedTabIndex = tabIndex) {
                        tabs.forEachIndexed { index, title ->
                            Tab(
                                text = { Text(title) },
                                selected = tabIndex == index,
                                onClick = { tabIndex = index },
                            )
                        }
                    }
                }
                item{
                    when(tabIndex){
                        0 -> Ingredients(viewModel, isLandscape)
                        1 -> Directions(viewModel, isLandscape)
                        2 -> Notes(viewModel, isLandscape)
                    }
                }
            }
        }else{
            Column(

                modifier = Modifier
                    .padding(values)
            ){

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
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp)
                )

                Text(
                    text = viewModel.cuisine,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
                TabRow(selectedTabIndex = tabIndex) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title) },
                            selected = tabIndex == index,
                            onClick = { tabIndex = index },
                        )
                    }
                }
                when(tabIndex){
                    0 -> Ingredients(viewModel, isLandscape)
                    1 -> Directions(viewModel, isLandscape)
                    2 -> Notes(viewModel, isLandscape)
                }
            }
        }
    }
}

@Composable
fun Ingredients(viewModel: RecipePageViewModel, isLandscape: Boolean){
    val listState = rememberLazyListState()
    if(isLandscape){
        LazyRow(
            state = listState,
            modifier = Modifier
                .padding(20.dp)
        ){
            item{
                Text(
                    text = viewModel.ingredients,
                    fontSize = 15.sp
                )
            }
        }
    }else{
        LazyColumn(state = listState,
            modifier = Modifier
                .padding(20.dp)){
            item{

                Text(
                    text = viewModel.ingredients,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun Directions(viewModel: RecipePageViewModel, isLandscape: Boolean){
    val listState = rememberLazyListState()
    if(isLandscape){
        LazyRow(
            state = listState,
            modifier = Modifier
                .padding(20.dp)
        ){
            item{
                Text(
                    text = viewModel.directions,
                    fontSize = 15.sp
                )
            }
        }
    }else{
        LazyColumn(state = listState,
            modifier = Modifier
                .padding(20.dp)){
            item{

                Text(
                    text = viewModel.directions,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun Notes(viewModel: RecipePageViewModel, isLandscape: Boolean){
    val listState = rememberLazyListState()
    if(isLandscape){
        LazyRow(
            state = listState,
            modifier = Modifier
                .padding(20.dp)
        ){
            item{
                Text(
                    text = viewModel.notes,
                    fontSize = 15.sp
                )
            }
        }
    }else{
        LazyColumn(state = listState,
            modifier = Modifier
                .padding(20.dp)){
            item{

                Text(
                    text = viewModel.notes,
                    fontSize = 15.sp
                )
            }
        }
    }
}


