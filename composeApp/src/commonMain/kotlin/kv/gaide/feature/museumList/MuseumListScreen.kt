package kv.gaide.feature.museumList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kv.gaide.common.ui.ErrorItem
import kv.gaide.common.ui.MuseumCard


@Composable
fun MuseumListScreen(
    viewModel: MuseumListViewModel = viewModel(),
    onMuseumClick: (String) -> Unit = {}
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
        if (uiState.isLoading) {
            item {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        if (uiState.errorMessage != null && !uiState.isLoading) {
            item {
                ErrorItem(
                    modifier = Modifier.fillMaxSize(),
                    errorMessage = uiState.errorMessage!!,
                    onRetryClick = { viewModel.getMuseumsList() }
                )
            }
        } else {
            items(uiState.museums) { museum ->
                MuseumCard(
                    modifier = Modifier.fillMaxWidth(),
                    museum = museum,
                    onClick = { onMuseumClick(museum.id) })
            }
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
