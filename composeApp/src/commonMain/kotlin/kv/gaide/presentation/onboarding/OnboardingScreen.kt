package kv.gaide.presentation.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun OnboardingScreen(
    pages: List<OnboardingPage>,
    onGetStarted: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val coroutineScope = rememberCoroutineScope()

    // Автоперелистывание
    LaunchedEffect(pagerState.currentPage) {
        while (true) {
            if (pagerState.currentPage < pages.size - 1) {
                delay(3000)
                val nextPage = (pagerState.currentPage + 1) % pages.size
                coroutineScope.launch {
                    pagerState.animateScrollToPage(nextPage)
                }
            } else {
                // На последней странице останавливаем автоперелистывание
                break
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            OnboardingPageContent(pageData = pages[page])
        }

        // Индикатор страниц (активный - вытянутый)
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 160.dp), // увеличили отступ, чтобы не налезало на кнопку
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pages.size) { index ->
                val isSelected = pagerState.currentPage == index
                val width by animateDpAsState(
                    targetValue = if (isSelected) 24.dp else 8.dp,
                    animationSpec = tween(durationMillis = 300)
                )
                Box(
                    modifier = Modifier
                        .width(width)
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            if (isSelected) MaterialTheme.colorScheme.primary
                            else Color.Gray
                        )
                )
                if (index < pages.size - 1) {
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }

        // Кнопка только на последней странице
        AnimatedVisibility(
            visible = pagerState.currentPage == pages.size - 1,
            modifier = Modifier.align(Alignment.BottomCenter),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Button(
                onClick = onGetStarted,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(bottom = 48.dp)
                    .width(200.dp)
                    .height(48.dp)
            ) {
                Text("Начать путешествие", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun OnboardingPageContent(pageData: OnboardingPage) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Полноэкранное изображение
        AsyncImage(
            model = pageData.imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Затемняющий градиент (чтобы текст был читаем)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 1f)
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )

        // Текстовый контент по центру
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = pageData.title,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = pageData.description,
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
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
        )
    ) {}
}

@Preview(showBackground = true)
@Composable
fun OnboardingPageContentPreview() {
    OnboardingPageContent(
        OnboardingPage(
            imageUrl = "",
            title = "Открой мир искусства",
            description = "Исследуй тысячи музеев мира, находи ближайшие выставки и планируй культурный досуг."
        )
    )
}
