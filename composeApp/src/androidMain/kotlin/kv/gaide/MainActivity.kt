package kv.gaide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kv.gaide.data.models.AuthState
import kv.gaide.presentation.HomeScreen
import kv.gaide.presentation.auth.AndroidAuthViewModel
import kv.gaide.presentation.auth.AndroidAuthViewModelFactory
import kv.gaide.presentation.auth.AuthScreen
import kv.gaide.presentation.auth.RegisterScreen
import kv.gaide.presentation.splash.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val application = application as MyApplication
            val authViewModel: AndroidAuthViewModel = viewModel(
                factory = AndroidAuthViewModelFactory(application.authRepository)
            )

            val navController = rememberNavController()
            val authState by authViewModel.state.collectAsState()

            LaunchedEffect(authState) {
                when (authState) {
                    is AuthState.Success -> navController.navigate("home") {
                        popUpTo("splash") { inclusive = true }
                    }

                    is AuthState.Idle -> { /* остаёмся на экране входа */
                    }

                    else -> {}
                }
            }

            NavHost(
                navController = navController,
                startDestination = "splash"
            ) {
                composable("splash") {
                    SplashScreen(
                        onAppStart = { authViewModel.checkAuth() },
                        navigateToAuth = { navController.navigate("auth") }
                    )
                }
                composable("auth") {
                    AuthScreen(
                        viewModel = authViewModel,
                        onNavigateToRegister = { navController.navigate("register") },
                        onNavigateBack = { navController.popBackStack() }
                    )
                }
                composable("register") {
                    RegisterScreen(
                        viewModel = authViewModel,
                        onNavigateBack = { navController.popBackStack() }
                    )
                }
                composable("home") {
                    HomeScreen()
                }
            }
        }
    }
}