package kv.gaide.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kv.gaide.data.repository.AuthRepository

class AndroidAuthViewModelFactory(
    private val repository: AuthRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AndroidAuthViewModel(repository) as T
    }
}