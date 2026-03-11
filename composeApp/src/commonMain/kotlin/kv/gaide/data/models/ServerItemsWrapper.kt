package kv.gaide.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ServerItemsWrapper<T>(
    val items: List<T>
)
