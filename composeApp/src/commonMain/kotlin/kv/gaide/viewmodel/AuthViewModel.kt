package kv.gaide.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kv.gaide.data.models.AuthState
import kv.gaide.data.models.LoginRequest
import kv.gaide.data.models.RegisterRequest
import kv.gaide.data.repository.AuthRepository

class AuthViewModel(
    private val repository: AuthRepository,
    private val coroutineScope: CoroutineScope
) {
    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)
    val state: StateFlow<AuthState> = _state.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow() // для регистрации

    fun updateEmail(value: String) {
        _email.value = value
    }

    fun updatePassword(value: String) {
        _password.value = value
    }

    fun updateName(value: String) {
        _name.value = value
    }

    fun login() {
        if (!isValidEmail(email.value)) {
            _state.value = AuthState.Error("Некорректный email")
            return
        }

        if (!isValidPassword(password.value)) {
            _state.value = AuthState.Error("Некорректный пароль")
            return
        }

        coroutineScope.launch {
            _state.value = AuthState.Loading
            val request = LoginRequest(email.value, password.value)
            val result = repository.login(request)
            _state.value = result.fold(
                onSuccess = { AuthState.Success },
                onFailure = { AuthState.Error(it.message ?: "Login failed") }
            )
        }
    }

    fun register() {
        if (!isValidEmail(email.value)) {
            _state.value = AuthState.Error("Некорректный email")
            return
        }

        if (!isValidPassword(password.value)) {
            _state.value = AuthState.Error("Некорректный пароль")
            return
        }

        coroutineScope.launch {
            _state.value = AuthState.Loading
            val request =
                RegisterRequest(email.value, password.value, name.value.takeIf { it.isNotBlank() })
            val result = repository.register(request)
            _state.value = result.fold(
                onSuccess = { AuthState.Success },
                onFailure = { AuthState.Error(it.message ?: "Registration failed") }
            )
        }
    }

    fun checkAuth() {
        val isAuth = false
        if (isAuth) {
            _state.value = AuthState.Success
        } else {
            _state.value = AuthState.Idle
        }
    }

    fun resetState() {
        _state.value = AuthState.Idle
    }

    private fun isValidEmail(email: String): Boolean {
        return true
    }

    private fun isValidPassword(password: String): Boolean {
        return true
    }
}