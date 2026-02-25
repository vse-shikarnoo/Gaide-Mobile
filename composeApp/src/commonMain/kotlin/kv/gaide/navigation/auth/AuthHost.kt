package kv.gaide.navigation.auth

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import kv.gaide.feature.auth.AuthEvent
import kv.gaide.feature.auth.AuthViewModel
import kv.gaide.feature.auth.LoginScreen
import kv.gaide.feature.auth.RegistrationScreen
import kv.gaide.feature.onboarding.OnboardingPage
import kv.gaide.feature.onboarding.OnboardingScreen
import kv.gaide.navigation.BackHandler

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuthHost(
    viewModel: AuthViewModel,
    navigationViewModel: AuthNavigationViewModel,
    onAuthSuccess: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val stack by navigationViewModel.stack.collectAsState()
    val current = stack.last()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                AuthEvent.LoginSuccess,
                AuthEvent.RegistrationSuccess -> {
                    onAuthSuccess()
                }
            }
        }
    }

    BackHandler(enabled = stack.size > 1) {
        navigationViewModel.pop()
    }

    Crossfade(targetState = current) { screen ->
        when (screen) {
            AuthScreens.Onboarding ->
                OnboardingScreen(
                    pages = listOf(
                        OnboardingPage(
                            imageUrl = "https://img.freepik.com/free-photo/people-looking-picture-art-gallery_119272-37.jpg?t=st=1771439495~exp=1771443095~hmac=970614cdebc91e252893a7ea29f12138cc24fca9084df29ae4bde7f08d886ae5&w=740",
                            title = "Открой мир искусства",
                            description = "Исследуй тысячи музеев мира, находи ближайшие выставки и планируй культурный досуг"
                        ),
                        OnboardingPage(
                            imageUrl = "https://img.freepik.com/free-photo/view-church-architectural-elements_23-2150319342.jpg?t=st=1771439447~exp=1771443047~hmac=95b1509c1f64590e2efb65c5da447cf47eaff4a31b23bfbcffd7eaec14f0bd68&w=1480",
                            title = "Личный гид в кармане",
                            description = " Аудиогиды, описания экспонатов и интерактивные карты помогут не пропустить самое интересное"
                        ),
                        OnboardingPage(
                            imageUrl = "https://img.freepik.com/free-photo/vertical-low-angle-shot-beautiful-paintings-carvings-old-building_181624-7919.jpg?t=st=1771439376~exp=1771442976~hmac=0b57c75ce7474072000dbe39f72a14829ae4e1e05cc6487ec51c9942df9c2c9b&w=1480",
                            title = "Сохраняй любимое",
                            description = "Добавляй музеи в избранное, составляй маршруты и делись впечатлениями с друзьями"
                        )
                    ),
                    onGetStarted = { navigationViewModel.push(AuthScreens.Login) }
                )

            AuthScreens.Login ->
                LoginScreen(
                    uiState = uiState,
                    onEmailChange = viewModel::updateEmail,
                    onPasswordChange = viewModel::updatePassword,
                    onLoginClick = viewModel::login,
                    onNavigateToRegister = { navigationViewModel.push(AuthScreens.Registration) }
                )

            AuthScreens.Registration ->
                RegistrationScreen(
                    uiState = uiState,
                    onEmailChange = viewModel::updateEmail,
                    onPasswordChange = viewModel::updatePassword,
                    onNameChange = viewModel::updateName,
                    onRegisterClick = viewModel::register,
                    onNavigateBack = { navigationViewModel.pop() }
                )
        }
    }
}