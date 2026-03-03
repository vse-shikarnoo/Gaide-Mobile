package kv.gaide.common.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gaide.composeapp.generated.resources.Res
import gaide.composeapp.generated.resources.hermitazh

enum class CardOrientation { Vertical, Horizontal }

@Composable
fun CardCustom(
    title: String,
    subtitle: String = "",
    orientation: CardOrientation = CardOrientation.Vertical,
    onClick: () -> Unit
) {
    when (orientation) {
        CardOrientation.Vertical -> CardVertical(title = title, onClick = onClick)
        CardOrientation.Horizontal -> CardHorizontal(
            title = title,
            subtitle = subtitle,
            onClick = onClick
        )
    }
}

@Composable
fun CardVertical(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .requiredWidth(200.dp)
            .clickable { onClick() },
        //elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AbstractFigure(
                imageRes = Res.drawable.hermitazh
            )

            TextWithNLines(
                modifier = Modifier.padding(top = 16.dp),
                text = title,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CardHorizontal(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String = "",
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
            AbstractFigure(
                modifier = Modifier.size(100.dp),
                imageRes = Res.drawable.hermitazh
            )
            Column(
                modifier = Modifier.weight(1f).padding(start = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextWithNLines(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    textStyle = MaterialTheme.typography.titleMedium
                )
                TextWithNLines(
                    modifier = Modifier.padding(top = 16.dp),
                    textStyle = MaterialTheme.typography.bodySmall,
                    text = subtitle,
                    maxLines = 3,
                    textAlign = TextAlign.Left
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardVerticalPreview() {
    CardVertical(
        title = "Title"
    ) {}
}

@Preview(showBackground = true)
@Composable
fun CardVerticalPreviewWithLongName() {
    CardVertical(
        title = "Музей современного искусства"
    ) {}
}

@Preview(showBackground = true)
@Composable
fun CardHorizontalPreview() {
    CardHorizontal(
        title = "Title",
        subtitle = "Subtitle"
    ) {}
}

@Preview(showBackground = true)
@Composable
fun CardHorizontalPreviewWithLongName() {
    CardHorizontal(
        title = "Музей современного искусства",
        subtitle = "Музей современного искусства на Манхеттене в Нью-Йорке (англ. Museum of Modern Art, сокр. MoMA) — один из первых и наиболее представительных музеев современного искусства в мире[6], послуживший эталоном для многих других собраний подобного рода.\n" +
                "\n" +
                "Третий по посещаемости музей в Соединённых Штатах, входит в первую двадцатку самых посещаемых художественных музеев мира[7] — 3 066 337 посетителей в 2013 году[8]. Одна из главных достопримечательностей Нью-Йорка."
    ) {}
}