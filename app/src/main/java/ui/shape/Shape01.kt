package ui.shape

import androidx.compose.foundation.border
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun ShapePreview() {
    Text(
        modifier = Modifier
            .border(width = 1.dp, Color.Black, shape = Shape01()),
        text = "Hello"
    )

}

class Shape01 : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val path = Path().apply {
            moveTo(size.width / 2f, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }

}
class RectangleShape(
    private val borderWidth: Float,
    private val borderHeight: Float,
    private val borderTopWidth: Float,
    private val borderBottomWidth: Float,
    private val borderStartWidth: Float,
    private val borderEndWidth: Float
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val rect = Rect(
            left = borderStartWidth,
            top = borderTopWidth,
            right = size.width - borderEndWidth,
            bottom = size.height - borderBottomWidth
        )
        val path = Path().apply {
            addRect(rect)
        }
        return Outline.Generic(path)
    }
}
