package com.cpp.recipebook
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



val listOfNames = listOf("Hamburger","Brownies", "Pie", "Butter Chicken","Fried Chicken")
val listOfCuisines = listOf("American", "Dessert", "Dessert", "Dessert", "Indian", "American")
val listOfImages = listOf(R.drawable.hamburger, R.drawable.brownies, R.drawable.pie, R.drawable.butterchicken, R.drawable.friedchicken)
@Composable
fun DisplayColumns(){
    Row(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        LazyColumn(
            modifier = Modifier
                //.weight(0.5f)
        ) {
            //items(listOfRecipes){ recipe ->
            items(listOfImages) { image ->
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                   ,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    ElevatedCard(
                        modifier = Modifier
                            .width(160.dp)
                            .height(150.dp)
                        ,

                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        )
                    ){
                        Surface(
                            color = Color.White
                        ){
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Image(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                                            //id = recipe.getImage()
                                    painter = painterResource(id = image),
                                    contentDescription = "Image",

                                    )
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .fillMaxWidth()
                                        .background(Color.White),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = "American Burger",
                                        modifier = Modifier.padding(start = 5.dp, top = 2.dp)

                                    )
                                    Text(
                                        text = "Cuisine",
                                        modifier = Modifier.padding(start = 5.dp, bottom = 2.dp)
                                    )
                                }
                            }
                        }



                    }

                }
            }
        }

        LazyColumn(
            modifier = Modifier
                //.weight(0.5f)
        ) {
            items(listOfImages) { image ->
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    ElevatedCard (
                        modifier = Modifier
                            .width(160.dp)
                            .height(150.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        )

                    ){
                        Surface(
                            color = Color.White
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Image(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    painter = painterResource(id = image),
                                    contentDescription = "Image",

                                    )
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .fillMaxWidth()
                                        .background(Color.White),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = "American Burger",
                                        modifier = Modifier.padding(start = 5.dp, top = 2.dp)

                                    )
                                    Text(
                                        text = "Cuisine",
                                        modifier = Modifier.padding(start = 5.dp, bottom = 2.dp)
                                    )
                                }
                            }
                        }
                    }

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
