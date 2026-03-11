package kv.gaide.feature.museumList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kv.gaide.data.remote.MuseumAPI
import kv.gaide.data.remote.Networking
import kv.gaide.data.repository.MuseumRepositoryImpl

class MuseumListViewModel : ViewModel() {
    private val repository = MuseumRepositoryImpl()

    private val _uiState = MutableStateFlow(MuseumListUiState())
    val uiState: StateFlow<MuseumListUiState> = _uiState


    init {
        getMuseumsList()
    }

    fun updateSearch(searchName: String) {
        _uiState.update {
            it.copy(
                searchName = searchName
            )
        }
    }

    fun getMuseumsList() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            repository.getMuseumsList().onSuccess { museums ->
                _uiState.update {
                    it.copy(
                        museums = museums,
                        errorMessage = null
                    )
                }
            }.onFailure { error ->
                _uiState.update {
                    it.copy(
                        errorMessage = error.message
                    )
                }
            }
            _uiState.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

    fun getMuseumsByCity(city: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            val result = if (city.isBlank()) {
                repository.getMuseumsList()
            }else{
                repository.getMuseumsByCity(city)
            }
            result.onSuccess { museums ->
                _uiState.update {
                    it.copy(
                        museums = museums,
                        errorMessage = null
                    )
                }
            }.onFailure { error ->
                _uiState.update {
                    it.copy(
                        errorMessage = error.message
                    )
                }
            }
            _uiState.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }


    fun getMuseumByName(name: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            val result = if (name.isBlank()) {
                repository.getMuseumsList()
            }else{
                repository.getMuseumByName(name)
            }
            result.onSuccess { museums ->
                _uiState.update {
                    it.copy(
                        museums = museums,
                        errorMessage = null
                    )
                }
            }.onFailure { error ->
                _uiState.update {
                    it.copy(
                        errorMessage = error.message
                    )
                }
            }
            _uiState.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }
}