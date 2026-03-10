package kv.gaide.feature.auth

sealed class AuthEvent {
    object LoginSuccess : AuthEvent()
    object RegistrationSuccess : AuthEvent()
}