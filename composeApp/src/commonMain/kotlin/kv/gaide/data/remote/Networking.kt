package kv.gaide.data.remote

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRedirect
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object Networking {

    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.d(message, tag = "Ktor")
                }
            }
            level = LogLevel.HEADERS
        }

        install(Logging) {
            level = LogLevel.BODY
        }

        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(
                        accessToken = "test-token-123",
                        refreshToken = "test-token-123"
                    )
                }
            }
        }

        install(HttpRedirect) {
            checkHttpMethod = false
        }

        defaultRequest {
            url("https://museums-vse-shikarnoo.amvera.io")
            contentType(ContentType.Application.Json)
        }
    }
}