package kv.gaide.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun TextWithNLines(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    maxLines: Int = 2,
    text: String
) {
    val lineHeightDp = with(LocalDensity.current) { textStyle.lineHeight.toDp() }

    Box(
        modifier = modifier.height((lineHeightDp * 2.1F)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            style = textStyle,
            textAlign = TextAlign.Center
        )
    }
}