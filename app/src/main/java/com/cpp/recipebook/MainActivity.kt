package com.cpp.recipebook

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cpp.recipebook.ui.create_update_recipe.CreateUpdateRecipeScreen
import com.cpp.recipebook.ui.recipe_list.RecipeListScreen
import com.cpp.recipebook.ui.recipe_page.RecipePage
import com.cpp.recipebook.ui.search.SearchScreen
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
                        composable(Routes.RECIPE_LIST + "?query={query}") {
                            RecipeListScreen(
                                onNavigate = { navController.navigate(it.route) }
                            )
                        }
                        composable(
                            route = Routes.CREATE_UPDATE_RECIPE + "?recipeId={recipeId}",
                            enterTransition = {
                                slideInHorizontally(
                                    animationSpec = tween(durationMillis = 450, easing = FastOutSlowInEasing)
                                ) {
                                        fullWidth -> fullWidth + 200
                                } + fadeIn()
                            },
                            exitTransition = {
                                slideOutHorizontally(
                                    animationSpec = tween(450, easing = FastOutSlowInEasing)
                                ) {
                                    fullWidth -> fullWidth + 200
                                }
                            },
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
                        composable(
                            route = Routes.RECIPE_PAGE + "?recipeId={recipeId}",
                            arguments = listOf(navArgument(name = "recipeId"){type = NavType.IntType})
                        ) {navBackStackEntry ->
                            val recipeId = navBackStackEntry.arguments?.getInt("recipeId")
                            recipeId?.let{id ->
                                RecipePage(recipeId = id)

                            }
                        }
                        composable(
                            route = Routes.SEARCH,
                            enterTransition = {
                                slideInHorizontally(
                                    animationSpec = tween(durationMillis = 450, easing = FastOutSlowInEasing)
                                ) {
                                    fullWidth -> fullWidth + 200
                                } + fadeIn()
                            },
                            exitTransition = {
                                slideOutHorizontally (
                                    animationSpec = tween(450, easing = FastOutSlowInEasing)
                                ) {
                                    fullWidth -> fullWidth + 200
                                } + fadeOut()
                            },
                        ) {
                            SearchScreen(
                                onPopBackStack = { navController.popBackStack() },
                                onNavigate = { navController.navigate(it.route) }
                            )
                        }
                    }
                )
            }
        }
    }
}
