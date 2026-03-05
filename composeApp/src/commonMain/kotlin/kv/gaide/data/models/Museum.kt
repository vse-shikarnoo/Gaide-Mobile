package kv.gaide.data.models

data class Museum(
    val id: Int,
    val name: String,
    val city: String,
    val imageRes: Int? = null,
    val imageUrl: String? = null,
    val description: String = "",
    val tags:List<String> = emptyList()
)
