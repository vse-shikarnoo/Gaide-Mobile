package kv.gaide.data.repository

import kv.gaide.data.models.Museum

interface MuseumRepository {
    suspend fun getMuseumsList():Result<List<Museum>>

    suspend fun getMuseumsByCity(city: String):Result<List<Museum>>

    suspend fun getMuseumByName(name: String):Result<List<Museum>>

}