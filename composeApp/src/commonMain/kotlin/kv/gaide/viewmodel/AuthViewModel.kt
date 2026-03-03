package kv.gaide.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kv.gaide.data.models.AuthState
import kv.gaide.data.models.LoginRequest
import kv.gaide.data.models.RegisterRequest
import kv.gaide.data.repository.AuthRepository
import kv.gaide.utils.PasswordStrength
import kv.gaide.utils.passwordStrength

class AuthViewModel(
    private val repository: AuthRepository,
) : ViewModel() {
    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)
    val state: StateFlow<AuthState> = _state.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()
    var emailError by mutableStateOf<String?>(null)
        private set

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()
    var passwordError by mutableStateOf<PasswordStrength>(PasswordStrength.BLANK)
        private set

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow() // для регистрации

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
        isValidEmail(newEmail)
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
        passwordError = newPassword.passwordStrength()
    }

    fun updateName(value: String) {
        _name.value = value
    }

    fun login() {
        if (!checkFields()) {
            return
        }

        viewModelScope.launch {
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
        if (!checkFields()) {
            return
        }

        viewModelScope.launch {
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

    private fun checkFields(): Boolean {
        isValidEmail(email.value)
        return (emailError == null || (passwordError != PasswordStrength.VERY_STRONG && passwordError != PasswordStrength.STRONG))
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

    private fun isValidEmail(email: String) {
        if (email.isBlank()) {
            emailError = "Email не может быть пустым"
        }
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        emailError = if (!email.matches(emailRegex.toRegex())) {
            "Введите валидный email"
        } else {
            null
        }
    }
}