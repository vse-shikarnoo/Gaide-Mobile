package kv.gaide.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kv.gaide.data.models.Museum
import kv.gaide.data.models.ServerItemsWrapper

class MuseumAPI(private val httpClient: HttpClient) {

    suspend fun getMuseums(search: String? = null): List<Museum> {
        return httpClient.get("/museums/") {
            parameter("search", search)
        }.body()
    }
}