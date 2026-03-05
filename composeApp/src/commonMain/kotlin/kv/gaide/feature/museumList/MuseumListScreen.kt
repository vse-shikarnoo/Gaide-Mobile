package kv.gaide.feature.museumList

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kv.gaide.common.ui.CardHorizontal
import kv.gaide.common.ui.CardVertical
import kv.gaide.common.ui.MuseumCard


@Composable
fun MuseumListScreen(
    viewModel: MuseumListViewModel = viewModel(),
    onMuseumClick: () -> Unit = {}
) {

    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            //.padding(16.dp)
            .padding(vertical = 16.dp)
    ) {
        item {
            SearchBar(
                search = uiState.searchName,
                onSearchValueChange = {
                    viewModel.updateSearch(it)
                    viewModel.getMuseumByName(it)
                }
            )
        }
        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                items(uiState.museums) { museum ->
                    CardVertical(
                        title = museum.name,
                        onClick = { onMuseumClick() })
                }
            }
        }
        items(uiState.museums) { museum ->
            MuseumCard(
                museum = museum,
                onClick = { onMuseumClick() })
            CardHorizontal(title = museum.name, onClick = { onMuseumClick() })
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

// Строка поиска
@Composable
fun SearchBar(
    modifier: Modifier = Modifier.padding(horizontal = 16.dp),
    search: String = "",
    onSearchValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = search,
        onValueChange = onSearchValueChange,
        placeholder = { Text("Search Museum") },
        leadingIcon = { },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        )
    )
}
