package kv.gaide.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kv.gaide.data.models.LoginRequest
import kv.gaide.data.models.RegisterRequest
import kv.gaide.data.repository.auth.AuthRepository
import kv.gaide.data.repository.auth.AuthRepositoryImpl
import kv.gaide.utils.PasswordStrength
import kv.gaide.utils.passwordStrength

class AuthViewModel() : ViewModel() {
    private val repository: AuthRepository = AuthRepositoryImpl()
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    private val _events = MutableSharedFlow<AuthEvent>()
    val events: SharedFlow<AuthEvent> = _events

    fun updateEmail(newEmail: String) {
        _uiState.update {
            it.copy(
                email = newEmail,
                emailError = isValidEmail(newEmail)
            )
        }
    }

    fun updatePassword(newPassword: String) {
        _uiState.update {
            it.copy(
                password = newPassword,
                passwordStrength = newPassword.passwordStrength()
            )
        }
    }

    fun updateName(newName: String) {
        _uiState.update {
            it.copy(
                name = newName
            )
        }
    }

    fun login() {
        if (!checkFields()) return

        clearError()
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true) }
            val request = LoginRequest(_uiState.value.email, _uiState.value.password)
            repository.login(request).onSuccess {
                _uiState.update {
                    it.copy(
                        isAuthenticated = true
                    )
                }
                _events.emit(AuthEvent.LoginSuccess)
            }.onFailure { error ->
                _uiState.update {
                    it.copy(
                        errorMessage = error.message,
                        isAuthenticated = false
                    )
                }
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun register() {
        viewModelScope.launch {
            if (!checkFields()) return@launch

            clearError()
            _uiState.update { it.copy(isLoading = true) }
            val request = RegisterRequest(
                _uiState.value.email,
                _uiState.value.password,
                _uiState.value.name.takeIf { it.isNotBlank() })

            repository.register(request).onSuccess {
                _uiState.update {
                    it.copy(
                        isAuthenticated = true
                    )
                }
                _events.emit(AuthEvent.RegistrationSuccess)
            }.onFailure { error ->
                _uiState.update {
                    it.copy(
                        errorMessage = error.message,
                        isAuthenticated = false
                    )
                }
            }

            _uiState.update { it.copy(isLoading = false) }

        }
    }

    private fun checkFields(): Boolean {
        _uiState.update {
            it.copy(
                emailError = isValidEmail(_uiState.value.email)
            )
        }
        return _uiState.value.emailError == null && (_uiState.value.passwordStrength == PasswordStrength.VERY_STRONG || _uiState.value.passwordStrength == PasswordStrength.STRONG)
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    private fun isValidEmail(email: String): String? {
        if (email.isBlank() || email.isEmpty()) {
            return "Email не может быть пустым"
        }
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        return if (!email.matches(emailRegex.toRegex())) {
            "Введите валидный email"
        } else {
            null
        }
    }
}