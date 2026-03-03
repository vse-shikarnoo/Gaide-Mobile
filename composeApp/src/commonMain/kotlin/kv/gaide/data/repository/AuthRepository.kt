package kv.gaide.data.repository

import kv.gaide.data.models.AuthResponse
import kv.gaide.data.models.LoginRequest
import kv.gaide.data.models.RegisterRequest

interface AuthRepository {
    suspend fun login(request: LoginRequest): Result<AuthResponse>
    suspend fun register(request: RegisterRequest): Result<AuthResponse>
}