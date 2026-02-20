package kv.gaide.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kv.gaide.data.models.AuthState
import kv.gaide.data.repository.AuthRepository
import kv.gaide.viewmodel.AuthViewModel

class AndroidAuthViewModel(
    repository: AuthRepository
) : ViewModel() {

    private val sharedViewModel = AuthViewModel(repository, viewModelScope)

    val state: StateFlow<AuthState> = sharedViewModel.state
    val email: StateFlow<String> = sharedViewModel.email
    val password: StateFlow<String> = sharedViewModel.password
    val name: StateFlow<String> = sharedViewModel.name

    fun updateEmail(value: String) = sharedViewModel.updateEmail(value)
    fun updatePassword(value: String) = sharedViewModel.updatePassword(value)
    fun updateName(value: String) = sharedViewModel.updateName(value)
    fun login() = sharedViewModel.login()
    fun register() = sharedViewModel.register()
    fun checkAuth() = sharedViewModel.checkAuth()
    fun resetState() = sharedViewModel.resetState()
}