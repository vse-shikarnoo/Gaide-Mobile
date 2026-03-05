package kv.gaide.common.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Tag(
    modifier: Modifier = Modifier,
    text: String
) {

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
            text = text
        )
    }


}

@Preview(showBackground = true)
@Composable
fun TagPreview() {
    Tag(
        text = "Greek"
    )
}