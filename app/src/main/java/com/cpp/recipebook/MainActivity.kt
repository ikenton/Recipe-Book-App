package com.cpp.recipebook

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cpp.recipebook.ui.create_update_recipe.CreateUpdateRecipeScreen
import com.cpp.recipebook.ui.recipe_list.RecipeListScreen
import com.cpp.recipebook.ui.theme.RecipeBookTheme
import com.cpp.recipebook.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeBookTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.RECIPE_LIST,
                    builder = {
                        composable(Routes.RECIPE_LIST) {
                            RecipeListScreen(
                                onNavigate = { navController.navigate(it.route) }
                            )
                        }
                        composable(
                            route = Routes.CREATE_UPDATE_RECIPE + "?recipeId={recipeId}",
                            arguments = listOf(
                                navArgument(name = "recipeId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                })
                            ) {
                                CreateUpdateRecipeScreen(
                                    onPopBackStack = { navController.popBackStack() }
                                )
                            }
                    }
                )
            }
        }
    }
}
