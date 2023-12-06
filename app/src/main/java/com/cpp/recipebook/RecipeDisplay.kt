package com.cpp.recipebook
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

//val repository = RecipeRepository.get()
val listOfNames = listOf("Hamburger","Brownies", "Pie", "Butter Chicken","Fried Chicken")
val listOfCuisines = listOf("American", "Dessert", "Dessert", "Dessert", "Indian", "American")
val listOfImages = listOf(R.drawable.hamburger, R.drawable.brownies, R.drawable.pie, R.drawable.butterchicken, R.drawable.friedchicken)
//var listOfRecipes = listOf(repository.getRecipes())
/*class RecipeDisplay (){
    private val args: RecipeDisplayArgs by navArgs()
    private var _binding: RecipeDisplayBinding? = null
    private val recipeRepository = RecipeRepository.get()
    private val recipeViewModel: RecipeViewModel by ViewModels{
        RecipeViewModelFactory(args.recipeId)
    }

    private var imageName: String? = null

    private val binding
        get() = checkNotNull(_binding){
            "Cannot access binding because it is null. Is the view visible?"
        }
}*/

@Composable
fun DisplayScreen(){
    Box {
        GenerateRows()
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
fun GenerateRows() {
    Column (
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 20.dp)
            .fillMaxWidth()
            ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){

        for (i in 0..listOfImages.size) {
            Row() {
                for (j in 0..1) {
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
                                        .fillMaxWidth(),
                                    //id = recipe.getImage()
                                    painter = painterResource(id = R.drawable.butterchicken),
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
                                        text = "listOfNames[i+j]",
                                        modifier = Modifier.padding(start = 5.dp, top = 2.dp)

                                    )
                                    Text(
                                        text = "listOfCuisines[i+j]",
                                        modifier = Modifier.padding(start = 5.dp, bottom = 2.dp)
                                    )
                                }
                            }
                        }

                    }
                    if (j == 0) {
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}
