package kv.gaide.data.repository

import kv.gaide.data.models.AuthResponse
import kv.gaide.data.models.LoginRequest
import kv.gaide.data.models.RegisterRequest
import kotlin.random.Random

class AuthRepositoryImpl() : AuthRepository {

    override suspend fun login(request: LoginRequest): Result<AuthResponse> = runCatching {
        val isAuth = Random.nextBoolean()
        val response: AuthResponse = AuthResponse(isAuth)
        response
    }

    override suspend fun register(request: RegisterRequest): Result<AuthResponse> = runCatching {
        val isAuth = Random.nextBoolean()
        val response: AuthResponse = AuthResponse(isAuth)
        response
    }
}