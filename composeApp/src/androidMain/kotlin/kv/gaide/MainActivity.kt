package kv.gaide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import kv.gaide.feature.auth.AndroidAuthViewModelFactory
import kv.gaide.feature.auth.AuthViewModel
import kv.gaide.navigation.auth.AuthNavigationViewModel
import kv.gaide.navigation.main.MainNavigationViewModel
import kv.gaide.navigation.root.RootNavigationViewModel

class MainActivity : ComponentActivity() {
    private val rootNavVM: RootNavigationViewModel by viewModels()
    private val authNavVM: AuthNavigationViewModel by viewModels()
    private val mainNavVM: MainNavigationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val application = application as MyApplication

            val authVM: AuthViewModel = viewModel(
                factory = AndroidAuthViewModelFactory(application.authRepository)
            )

            App(
                rootNavigationViewModel = rootNavVM,
                authNavigationViewModel = authNavVM,
                mainNavigationViewModel = mainNavVM,
                authViewModel = authVM
            )
        }
    }
}

//            NavHost(
//                navController = navController,
//                startDestination = "splash"
//            ) {
//                composable("splash") {
//                    OnboardingScreen(
//                        pages = listOf(
//                            OnboardingPage(
//                                imageUrl = "https://img.freepik.com/free-photo/people-looking-picture-art-gallery_119272-37.jpg?t=st=1771439495~exp=1771443095~hmac=970614cdebc91e252893a7ea29f12138cc24fca9084df29ae4bde7f08d886ae5&w=740",
//                                title = "Открой мир искусства",
//                                description = "Исследуй тысячи музеев мира, находи ближайшие выставки и планируй культурный досуг"
//                            ),
//                            OnboardingPage(
//                                imageUrl = "https://img.freepik.com/free-photo/view-church-architectural-elements_23-2150319342.jpg?t=st=1771439447~exp=1771443047~hmac=95b1509c1f64590e2efb65c5da447cf47eaff4a31b23bfbcffd7eaec14f0bd68&w=1480",
//                                title = "Личный гид в кармане",
//                                description = " Аудиогиды, описания экспонатов и интерактивные карты помогут не пропустить самое интересное"
//                            ),
//                            OnboardingPage(
//                                imageUrl = "https://img.freepik.com/free-photo/vertical-low-angle-shot-beautiful-paintings-carvings-old-building_181624-7919.jpg?t=st=1771439376~exp=1771442976~hmac=0b57c75ce7474072000dbe39f72a14829ae4e1e05cc6487ec51c9942df9c2c9b&w=1480",
//                                title = "Сохраняй любимое",
//                                description = "Добавляй музеи в избранное, составляй маршруты и делись впечатлениями с друзьями"
//                            )
//                        ),
//                        onGetStarted = { navController.navigate("auth") }
//                    )
//                }
//                composable("auth") {
//                    AuthScreen(
//                        viewModel = authViewModel,
//                        onNavigateToRegister = { navController.navigate("register") },
//                        onNavigateBack = { navController.popBackStack() }
//                    )
//                }
//                composable("register") {
//                    RegisterScreen(
//                        viewModel = authViewModel,
//                        onNavigateBack = { navController.popBackStack() }
//                    )
//                }
//                composable("home") {
//                    HomeScreen()
//                }
//            }