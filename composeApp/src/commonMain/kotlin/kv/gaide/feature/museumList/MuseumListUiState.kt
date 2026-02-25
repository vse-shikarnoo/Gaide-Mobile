package kv.gaide.feature.museumList

data class MuseumListUiState(
    val museums: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)