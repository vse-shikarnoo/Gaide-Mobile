package kv.gaide.feature.museumList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kv.gaide.data.models.Museum


@Composable
fun MuseumListScreen(
    viewModel: MuseumListViewModel = viewModel(),
    onMuseumClick: () -> Unit = {}
) {

    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(horizontal = 16.dp)
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
        item { CategoryChips() }
        items(uiState.museums) { museum ->
            MuseumCard(museum = museum, onClick = { onMuseumClick() })
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

// Строка поиска
@Composable
fun SearchBar(
    search: String = "",
    onSearchValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = search,
        onValueChange = onSearchValueChange,
        placeholder = { Text("Search Museum") },
        leadingIcon = { },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        )
    )
}

// Чипсы категорий (Statue, Ceramic, Paint, Docu)
@Composable
fun CategoryChips() {
    val categories = listOf("Statue", "Ceramic", "Paint", "Docu")
    LazyRow(
        modifier = Modifier.padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            AssistChip(
                onClick = {},
                label = { Text(category) },
                //modifier = Modifier.clip(RoundedCornerShape(16.dp))
            )
        }
    }
}

// Карточка музея
@Composable
fun MuseumCard(museum: Museum, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = museum.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = museum.city, color = Color.Gray, fontSize = 14.sp)
            }
        }
    }
}
