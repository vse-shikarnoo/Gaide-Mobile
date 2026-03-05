package kv.gaide

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import kv.gaide.navigation.AppNavigation

@Composable
fun App() {

    MaterialTheme {
        AppNavigation()
    }
}