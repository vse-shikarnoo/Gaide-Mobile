package kv.gaide.feature.museumList

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MuseumListViewModel {

    private val _uiState = MutableStateFlow(
        MuseumListUiState(
            museums = listOf(
                "Исторический музей",
                "Музей искусства",
                "Научный музей"
            )
        )
    )

    val uiState: StateFlow<MuseumListUiState> = _uiState
}