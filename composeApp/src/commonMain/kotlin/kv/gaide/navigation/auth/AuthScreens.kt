package kv.gaide.navigation.auth

sealed class AuthScreens {
    object Onboarding : AuthScreens()
    object Login : AuthScreens()
    object Registration : AuthScreens()

    fun toRoute(): String = when (this) {
        Onboarding -> "onboarding"
        Login -> "login"
        Registration -> "registration"
    }

    companion object {
        fun fromRoute(route: String): AuthScreens =
            when (route) {
                "onboarding" -> Onboarding
                "login" -> Login
                "registration" -> Registration
                else -> Login
            }
    }
}