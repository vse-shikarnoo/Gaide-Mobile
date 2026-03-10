package kv.gaide.feature.auth

import kv.gaide.utils.PasswordStrength

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val name: String = "",

    val emailError: String? = null,
    val passwordStrength: PasswordStrength = PasswordStrength.BLANK,

    val isLoading: Boolean = false,
    val errorMessage: String? = null,

    val isAuthenticated: Boolean = false
)