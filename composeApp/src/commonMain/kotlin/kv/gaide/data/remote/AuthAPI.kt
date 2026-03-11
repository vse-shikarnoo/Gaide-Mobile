package kv.gaide.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kv.gaide.data.models.AuthResponse
import kv.gaide.data.models.LoginRequest
import kv.gaide.data.models.RegisterRequest

class AuthAPI (private val httpClient: HttpClient) {

    suspend fun register(request: RegisterRequest): AuthResponse {
        return httpClient.post("/auth/register") {
            parameter("name", request.name)
            parameter("email", request.email)
            parameter("password", request.password)
        }.body()
    }

    suspend fun login(request: LoginRequest): AuthResponse {
        return httpClient.post("/auth/login") {
            parameter("email", request.email)
            parameter("password", request.password)
        }.body()
    }
}