package kv.gaide.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp


@Composable
fun GlassPanel(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.matchParentSize()
                .clip(RoundedCornerShape(16.dp))
                .graphicsLayer {
                    renderEffect = BlurEffect(
                        30f,
                        30f,
                    )
                    compositingStrategy = CompositingStrategy.Offscreen
                }
                .background(Color.White.copy(alpha = 0.15f))
                .border(
                    1.dp,
                    Color.White.copy(alpha = 0.3f),
                    RoundedCornerShape(16.dp)
                )
        )
        content()
    }
}