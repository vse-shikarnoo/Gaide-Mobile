package kv.gaide.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kv.gaide.feature.museumList.MuseumListScreen
import kv.gaide.navigation.auth.AuthNavGraph

object MainDestinations {
    const val Auth = "auth_graph"
    const val Main = "main_screen"
}

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = MainDestinations.Auth
    ) {
        composable(MainDestinations.Auth) {
            AuthNavGraph(
                onAuthSuccess = {
                    navController.navigate(MainDestinations.Main) {
                        popUpTo(MainDestinations.Auth) { inclusive = true }
                    }
                }
            )
        }
        composable(MainDestinations.Main) {
            MuseumListScreen(
                onMuseumClick = {},
                onProfileClick = {  }
            )
        }
    }
}