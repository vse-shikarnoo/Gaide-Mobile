package kv.gaide.data.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String?
)

@Serializable
data class AuthResponse(
    val isAuth: Boolean,
)