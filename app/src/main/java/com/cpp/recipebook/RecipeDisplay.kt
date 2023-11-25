package com.cpp.recipebook
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview


val listOfNames = listOf("American Pie", "Tacos")
val listOfCuisines = listOf("American", "Mexican")
val listOfImages = listOf(R.drawable.hamburger, R.drawable.ic_launcher_background)
@Composable
fun DisplayColumns(){
    Row(

        modifier = Modifier.fillMaxWidth()
        //verticalAlignment = Alignment.Top
    ) {
        LazyColumn {
            items(listOfImages) { image ->
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = "Image",

                        )
                    Text(text = "American Burger")
                    Text(text = "Cuisine")
                }
            }
        }

        LazyColumn {
            items(listOfImages) { image ->
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = "Image",

                        )
                    Text(text = "Pie")
                    Text(text = "Cuisine")
                }
            }
        }
    }
}

@Preview
@Composable
fun ComposablePreview(){
    DisplayColumns()
}
