package kv.gaide.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp


@Composable
fun GlassPanel(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier
    ) {
        Box(
            modifier = Modifier.matchParentSize()
                .graphicsLayer {
                    renderEffect = BlurEffect(
                        30f,
                        30f,
                    )
                    compositingStrategy = CompositingStrategy.Offscreen
                }
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                    RoundedCornerShape(16.dp)
                )
        )
        content()
    }
}
