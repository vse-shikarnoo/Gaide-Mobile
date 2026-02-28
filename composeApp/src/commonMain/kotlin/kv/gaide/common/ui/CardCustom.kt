package kv.gaide.common.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class CardOrientation { Vertical, Horizontal }

@Composable
fun CardCustom(
    name: String,
    orientation: CardOrientation = CardOrientation.Vertical,
    onClick: () -> Unit
) {
    when (orientation) {
        CardOrientation.Vertical -> CardVertical(name = name, onClick = onClick)
        CardOrientation.Horizontal -> CardHorizontal(name, onClick)
    }
}

@Composable
fun CardVertical(
    modifier: Modifier = Modifier,
    name: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .requiredWidth(200.dp)
            .requiredHeight(300.dp)
            .padding(16.dp)
            .clickable { onClick() },
        //elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Canvas(
                modifier = Modifier.size(200.dp)
            ) {
                drawCircle(
                    color = Color.Blue,
                    radius = size.minDimension / 2,
                    center = center,
                    style = Stroke(width = 4.dp.toPx())  // если нужна обводка, иначе заливка
                )
            }

            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun CardHorizontal(
    name: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() },
        //elevation = 4.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardVerticalPreview() {
    CardVertical(
        name = "Test"
    ) {}
}