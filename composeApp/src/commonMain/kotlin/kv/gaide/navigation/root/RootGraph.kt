package kv.gaide.navigation.root

sealed class RootGraph {
    object Auth : RootGraph()
    object Main : RootGraph()

    fun toRoute(): String = when (this) {
        Auth -> "auth"
        Main -> "main"
    }

    companion object {
        fun fromRoute(route: String): RootGraph =
            when (route) {
                "auth" -> Auth
                "main" -> Main
                else -> Auth
            }
    }
}