package kv.gaide.common.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gaide.composeapp.generated.resources.Res
import gaide.composeapp.generated.resources.hermitazh
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AbstractFigure(
    imageRes: DrawableResource? = null,
    modifier: Modifier = Modifier.size(150.dp),
    primaryColor: Color = MaterialTheme.colorScheme.primary,
    secondaryColor: Color = MaterialTheme.colorScheme.secondary
){
    BoxWithConstraints(
        modifier = modifier,
        //contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = modifier
        ) {
            drawArc(
                color = primaryColor,
                startAngle = 0f,
                sweepAngle = -180f,
                useCenter = true,
            )
            drawArc(
                color = secondaryColor,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(size.height/12F, size.width/12F),
                size = size/ 1.21F
            )
        }
        if (imageRes != null){
            Image(
                painter = painterResource(imageRes),
                contentDescription = "Описание изображения",
                modifier = Modifier.size(maxWidth/1.5F).offset(
                    maxWidth/6,
                    maxHeight/10
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AbstractFigurePreview(){
    AbstractFigure(Res.drawable.hermitazh)
}