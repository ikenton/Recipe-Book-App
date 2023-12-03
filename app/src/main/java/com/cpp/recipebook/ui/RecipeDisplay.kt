package com.cpp.recipebook.ui
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cpp.recipebook.R

//val repository = RecipeRepository.get()
val listOfNames = listOf("Hamburger","Brownies", "Pie", "Butter Chicken","Fried Chicken")
val listOfCuisines = listOf("American", "Dessert", "Dessert", "Indian", "American")
val listOfImages =listOf(
    R.drawable.hamburger,
    R.drawable.brownies,
    R.drawable.pie,
    R.drawable.butterchicken,
    R.drawable.friedchicken
)
/*val recipeRepository = RecipeRepository.get()
val recipes = recipeRepository.getRecipes()*/
@Composable
fun DisplayScreen(){

    Box {
        GenerateRows(listOfImages, listOfNames, listOfCuisines)
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(25.dp)
        ) {
            PlusButton()
        }
    }
}
@Composable
fun PlusButton(){
    Button(
        onClick = {


        },
        shape = CircleShape
        ){
        Image(
            painterResource(id = R.drawable.ic_plus),
            contentDescription = "Create Recipe"
        )
    }
}
@Composable
fun CreateRecipeButton(){
    Button(onClick = { /*TODO*/ }) {

    }
}

@Composable
fun GenerateRows(images: List<Int>, names: List<String>, cuisines: List<String>) {
    var index = 0
    Column (
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 20.dp)
            .fillMaxWidth()
            ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        val total = kotlin.math.ceil((images.size / 2.0))

        for (i in 1..total.toInt()) {
            Row() {
                for (j in 0..1) {
                    if (index >= images.size) {

                        ElevatedCard(
                            modifier = Modifier
                                .width(160.dp)
                                .height(150.dp)
                            //.padding(bottom = 20.dp)
                            ,
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 6.dp
                            )
                        ) {
                            Surface(
                                color = Color.White
                            ) {}
                        }
                    }else{
                        ElevatedCard(
                            modifier = Modifier
                                .width(160.dp)
                                .height(150.dp)
                                //.padding(bottom = 20.dp)
                            ,
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 6.dp
                            )
                        ) {
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
                                            //id = recipe.getImage()
                                            painter = painterResource(id = images[index]),
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
                                            text = names[index],
                                            modifier = Modifier.padding(
                                                start = 5.dp,
                                                top = 2.dp
                                        )

                                    )
                                        Text(
                                            text = cuisines[index],
                                            modifier = Modifier.padding(
                                                start = 5.dp,
                                                bottom = 2.dp
                                            )
                                        )
                                    }
                                }
                            }
                        }
                        if (j == 0) {
                            Spacer(modifier = Modifier.width(20.dp))
                        }
                    }
                    index++

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}

