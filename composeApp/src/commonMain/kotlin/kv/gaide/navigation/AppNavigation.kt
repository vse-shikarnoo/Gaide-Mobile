package kv.gaide.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kv.gaide.navigation.auth.AuthNavGraph
import kv.gaide.navigation.main.MainNavGraph

object RouteDestinations {
    const val Auth = "auth_graph"
    const val Main = "main_screen"
}

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = RouteDestinations.Auth
    ) {
        composable(RouteDestinations.Auth) {
            AuthNavGraph(
                onAuthSuccess = {
                    navController.navigate(RouteDestinations.Main) {
                        popUpTo(RouteDestinations.Auth) { inclusive = true }
                    }
                }
            )
        }
        composable(RouteDestinations.Main) {
            MainNavGraph()
        }
    }
}