package kv.gaide.navigation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kv.gaide.feature.detailMuseum.DetailMuseumScreen
import kv.gaide.feature.museumList.MuseumListScreen
import androidx.navigation.navArgument

@Composable
fun MainNavGraph(
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainDestinations.MuseumList.route
    ) {
        composable(MainDestinations.MuseumList.route) {
            MuseumListScreen { museumId ->
                navController.navigate(
                    MainDestinations.DetailMuseum.createRoute(museumId)
                )
            }
        }

        composable(
            route = MainDestinations.DetailMuseum.route,
            arguments = listOf(
                navArgument("museumId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val museumId = backStackEntry.arguments?.getInt("museumId") ?: 0

            DetailMuseumScreen(museumId)
        }
    }
}