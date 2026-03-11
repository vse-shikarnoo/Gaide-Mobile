package kv.gaide.navigation.auth

private const val AUTH_ROUTE = "auth"
sealed class AuthDestinations(
    val route: String
) {
    object Onboarding : AuthDestinations(
        "$AUTH_ROUTE/onboarding"
    )
    object Login : AuthDestinations(
        "$AUTH_ROUTE/login"
    )
    object Registration : AuthDestinations(
        "$AUTH_ROUTE/registration"
    )
}