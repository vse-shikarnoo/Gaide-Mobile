package kv.gaide.navigation.main

sealed class MainScreens {

    object MuseumList : MainScreens()

    data class Museum(val museumId: String) : MainScreens()

    data class Hall(val hallId: String) : MainScreens()

    data class Exhibit(val exhibitId: String) : MainScreens()

    object Profile : MainScreens()
    object Favorites : MainScreens()
    data class Map(val museumId: String) : MainScreens()
}