package kv.gaide.feature.museumList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MuseumListScreen(
    uiState: MuseumListUiState,
    onMuseumClick: (String) -> Unit,
    onProfileClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            "Музеи",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))

        uiState.museums.forEach { museum ->
            Button(
                onClick = { onMuseumClick(museum) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(museum)
            }

            Spacer(Modifier.height(8.dp))
        }

        Spacer(Modifier.weight(1f))

        Button(onClick = onProfileClick) {
            Text("Профиль")
        }
    }
}