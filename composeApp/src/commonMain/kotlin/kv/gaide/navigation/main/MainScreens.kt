package kv.gaide.navigation.main

sealed class MainScreens {

    object MuseumList : MainScreens()

    data class Museum(val museumId: String) : MainScreens()

    data class Hall(val hallId: String) : MainScreens()

    data class Exhibit(val exhibitId: String) : MainScreens()

    object Profile : MainScreens()
    object Favorites : MainScreens()
    data class Map(val museumId: String) : MainScreens()

    fun toRoute(): String = when (this) {
        MuseumList -> "museum_list"
        is Museum -> "museum/$museumId"
        is Hall -> "hall/$hallId"
        is Exhibit -> "exhibit/$exhibitId"
        Profile -> "profile"
        Favorites -> "favourites"
        is Map -> "map/$museumId"
    }

    companion object {
        fun fromRoute(route: String): MainScreens {
            return when {
                route == "museum_list" -> MuseumList
                route.startsWith("museum/") ->
                    Museum(route.removePrefix("museum/"))

                route.startsWith("hall/") ->
                    Hall(route.removePrefix("hall/"))

                route.startsWith("exhibit/") ->
                    Exhibit(route.removePrefix("exhibit/"))

                route == "profile" -> Profile
                else -> MuseumList
            }
        }
    }
}