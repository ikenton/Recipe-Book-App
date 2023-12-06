package com.cpp.recipebook

/*
enum class Navigation(@StringRes val title: Int){
    Start(title = R.string.navigation),
    RecipeCreation(title = R.string.recipe_creation_page),
    RecipePage(title = R.string.recipe_page)
}

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeAppBar(
    currentScreen: Navigation,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeBookApp(
    viewModel: RecipeViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Navigation.valueOf(
        backStackEntry?.destination?.route ?: Navigation.Start.name
    )
    Scaffold {innerPadding ->
       // val state by viewModel.state.collectAsState()
        var topBar = {
            RecipeAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        }
        NavHost(
            navController = navController,
            startDestination = Navigation.Start.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(
                route = Navigation.Start.name
            ){
                DisplayScreen(
                    onAddButtonClicked = {
                        navController.navigate(Navigation.RecipeCreation.name)
                    }
                )
            }



        }
    }
}
*/










