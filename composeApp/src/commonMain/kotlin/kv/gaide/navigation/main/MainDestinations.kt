package kv.gaide.navigation.main

private const val MAIN_ROUTE = "main"

sealed class MainDestinations(val route: String) {

    object MuseumList : MainDestinations("$MAIN_ROUTE/museum_list")

    object DetailMuseum : MainDestinations("$MAIN_ROUTE/detail_museum/{museumId}") {
        fun createRoute(museumId: String) = "$MAIN_ROUTE/detail_museum/$museumId"
    }

    object Hall : MainDestinations("$MAIN_ROUTE/hall/{hallId}") {
        fun createRoute(hallId: Int) = "$MAIN_ROUTE/hall/$hallId"
    }

    object Exhibit : MainDestinations("$MAIN_ROUTE/exhibit/{exhibitId}") {
        fun createRoute(exhibitId: Int) = "$MAIN_ROUTE/exhibit/$exhibitId"
    }

    object Profile : MainDestinations("$MAIN_ROUTE/profile")

    object Favorites : MainDestinations("$MAIN_ROUTE/favorites")

    object Map : MainDestinations("$MAIN_ROUTE/map/{museumId}") {
        fun createRoute(museumId: String) = "$MAIN_ROUTE/map/$museumId"
    }
}