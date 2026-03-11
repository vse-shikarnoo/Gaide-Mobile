package kv.gaide.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Museum(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("city")
    val city: String,
    val imageRes: Int? = null,
    @SerialName("main_image_url")
    val imageUrl: String? = null,
    @SerialName("description")
    val description: String = "",
    @SerialName("tags")
    val tags:List<String> = emptyList()
)
