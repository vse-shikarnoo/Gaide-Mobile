package kv.gaide.data.models

import kotlinx.serialization.SerialName
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
    @SerialName("access_token")
    val token: String
)