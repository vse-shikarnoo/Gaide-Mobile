package kv.gaide

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kv.gaide.feature.auth.AuthViewModel
import kv.gaide.navigation.auth.AuthHost
import kv.gaide.navigation.auth.AuthNavigationViewModel
import kv.gaide.navigation.main.MainHost
import kv.gaide.navigation.main.MainNavigationViewModel
import kv.gaide.navigation.root.RootGraph
import kv.gaide.navigation.root.RootNavigationViewModel

@Composable
fun App(
    rootNavigationViewModel: RootNavigationViewModel,
    authNavigationViewModel: AuthNavigationViewModel,
    mainNavigationViewModel: MainNavigationViewModel,
    authViewModel: AuthViewModel
) {
    MaterialTheme {
        val graph by rootNavigationViewModel.graph.collectAsState()

        when (graph) {
            RootGraph.Auth ->
                AuthHost(
                    viewModel = authViewModel,
                    navigationViewModel = authNavigationViewModel,
                    onAuthSuccess = {
                        authNavigationViewModel.clear()
                        rootNavigationViewModel.openMain()
                    }
                )

            RootGraph.Main ->
                MainHost(
                    navigationViewModel = mainNavigationViewModel,
                    onLogout = {
                        mainNavigationViewModel.clear()
                        rootNavigationViewModel.openAuth()
                    }
                )
        }
    }
}