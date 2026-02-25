package kv.gaide.feature.museumList

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MuseumListViewModel : ViewModel() {

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