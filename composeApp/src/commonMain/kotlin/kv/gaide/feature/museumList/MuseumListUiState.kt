package kv.gaide.feature.museumList

import kv.gaide.data.models.Museum

data class MuseumListUiState(
    val museums: List<Museum> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val city: String? = null,
    val searchName: String = ""
)