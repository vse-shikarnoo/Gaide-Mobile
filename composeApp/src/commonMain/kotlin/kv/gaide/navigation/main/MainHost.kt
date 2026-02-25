package kv.gaide.navigation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import kv.gaide.feature.museumList.MuseumListScreen
import kv.gaide.feature.museumList.MuseumListViewModel
import kv.gaide.navigation.BackHandler

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainHost(
    navigationViewModel: MainNavigationViewModel,
    onLogout: () -> Unit
) {

    val stack by navigationViewModel.stack.collectAsState()
    val current = stack.last()

    BackHandler(enabled = stack.size > 1) {
        navigationViewModel.pop()
    }

    when (current) {

        MainScreens.MuseumList -> {
            val vm = remember { MuseumListViewModel() }
            val state by vm.uiState.collectAsState()

            MuseumListScreen(
                uiState = state,
                onMuseumClick = { navigationViewModel.push(MainScreens.Museum(it)) },
                onProfileClick = { navigationViewModel.push(MainScreens.Profile) }
            )
        }

        else -> {}
    }
}