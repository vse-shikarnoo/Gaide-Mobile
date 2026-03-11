package kv.gaide.data.repository.auth

import kv.gaide.data.models.AuthResponse
import kv.gaide.data.models.LoginRequest
import kv.gaide.data.models.RegisterRequest
import kv.gaide.data.remote.AuthAPI
import kv.gaide.data.remote.Networking
import kotlin.random.Random

class AuthRepositoryImpl() : AuthRepository {

    private val authAPI = AuthAPI(Networking.httpClient)

    override suspend fun login(request: LoginRequest): Result<AuthResponse> = runCatching {
        authAPI.login(request)
    }

    override suspend fun register(request: RegisterRequest): Result<AuthResponse> = runCatching {
        authAPI.register(request)
    }
}