package kv.gaide.feature.detailMuseum

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.composables.icons.materialicons.MaterialIcons
import com.composables.icons.materialicons.filled.Keyboard_arrow_down
import kv.gaide.common.ui.MuseumCard
import kv.gaide.feature.museumList.MuseumListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMuseumScreen(
    museumId: Int,
    viewModel: DetailMuseumViewModel = viewModel(),
    museumViewModel: MuseumListViewModel = viewModel(),
) {
    val uiState by museumViewModel.uiState.collectAsState()

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text("Museums")
                }
            )
        }
    ) { padding ->

        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            contentPadding = padding
        ) {

            // Collapsing header
            item(span = { GridItemSpan(maxLineSpan) }) {
                MuseumInfo()
            }

            // Grid items
            items(uiState.museums) { museum ->
                MuseumCard(museum) {}
            }
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
                    "Детали"
                )
            }
        }
        ExpandableText(
            "Очень длинный текст который занимает много строк. " +
                    "Он изначально обрезается до четырех строк. " +
                    "Снизу есть градиент и стрелка. " +
                    "При нажатии текст плавно раскрывается полностью." +
                    "Очень длинный текст который занимает много строк. " +
                    "Он изначально обрезается до четырех строк. " +
                    "Снизу есть градиент и стрелка. " +
                    "При нажатии текст плавно раскрывается полностью." +
                    "Очень длинный текст который занимает много строк. " +
                    "Он изначально обрезается до четырех строк. " +
                    "Снизу есть градиент и стрелка. " +
                    "При нажатии текст плавно раскрывается полностью."
        )
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

@Composable
fun ExpandableText(
    text: String,
    collapsedMaxLines: Int = 4,
    modifier: Modifier = Modifier
) {

    var expanded by rememberSaveable { mutableStateOf(false) }
    var canExpand by rememberSaveable { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = ""
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
            .background(Color.Yellow)
    ) {

        Text(
            text = text,
            maxLines = if (expanded) Int.MAX_VALUE else collapsedMaxLines,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            onTextLayout = { result ->
                canExpand = result.hasVisualOverflow
            },
            modifier = Modifier.fillMaxWidth()
        )

        if (canExpand && !expanded) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.background
                            )
                        )
                    )
            )
        }

        if (canExpand) {
            IconButton(
                onClick = { expanded = !expanded },
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Icon(
                    imageVector = MaterialIcons.Filled.Keyboard_arrow_down,
                    contentDescription = "Expand text",
                    modifier = Modifier.rotate(rotation)
                )
            }
        }
    }
}