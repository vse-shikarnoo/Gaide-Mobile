package kv.gaide.feature.detailMuseum

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kv.gaide.common.ui.MuseumCard
import kv.gaide.feature.museumList.MuseumListViewModel

@Composable
fun DetailMuseumScreen(
    museumId: Int,
    viewModel: DetailMuseumViewModel = viewModel(),
    museumViewModel: MuseumListViewModel = viewModel(),
) {
    val uiState by museumViewModel.uiState.collectAsState()

    LazyColumn {
        item {
            MuseumInfo()
        }
        items(uiState.museums) {
            MuseumCard(it) {}
        }
    }
}

@Composable
fun MuseumInfo() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.weight(1f).aspectRatio(1f)
                    .background(Color.Red)
            ) {
                Text(
                    "Фото"
                )
            }
            Box(
                modifier = Modifier.weight(1f).aspectRatio(1f)
                    .background(Color.Blue)
            ) {
                Text(
                    "Описание"
                )
            }
        }
        Box(
            modifier = Modifier.background(Color.Yellow).requiredHeight(100.dp).fillMaxWidth()
        ) {
            Text(
                "Описание"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailMuseumScreenPreview() {
    DetailMuseumScreen(0)
}

@Preview(showBackground = true)
@Composable
fun MuseumInfoPreview() {
    MuseumInfo()
}