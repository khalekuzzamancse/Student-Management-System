package ui.table

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class TableContent(
    val text: String,
    val height: Float = 0f,
    val width: Float = 0f,
    val coordinate: Offset = Offset.Zero
)

@Preview
@Composable
private fun TextSizeMeasurement() {
    val textMeasurer = rememberTextMeasurer()
    val padding = 16.dp // Example padding value

    val headerContents = mutableListOf(
        TableContent("Roll"),
        TableContent("Name"),
        TableContent("Department"),
        TableContent("Section")
    )

    val getTextWidth:(String)->Float={
        textMeasurer.measure(it).size.width.toFloat()
    }
    val getTextHeight:(String)->Float={
        textMeasurer.measure(it).size.height.toFloat()
    }


    var previousX = 0f
    val y = 0f

    headerContents.forEachIndexed { index, content ->
        val textWidth =getTextWidth(content.text)
        val textHeight = getTextHeight(content.text)
        val x = previousX + padding.value // Add padding to the x-coordinate
        previousX += textWidth + padding.value // Add padding to the next x-coordinate
        headerContents[index] = content.copy(width = textWidth, height = textHeight, coordinate = Offset(x, y))
    }

    val rowWidth =
        headerContents.last().coordinate.x + headerContents.last().width + padding.value

    Canvas(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        headerContents.forEach { content ->
            drawText(
                topLeft = content.coordinate,
                textMeasurer = textMeasurer,
                text = content.text
            )
        }
    }
}



/*
  drawLine(
            start = Offset(0f, 0f),
            end = Offset(rowWidth, 00f),
            color = Color.Black
        )
        val columnHeight = getTextHeight(headerTexts.first())
        drawLine(
            start = Offset(0f, columnHeight),
            end = Offset(rowWidth, columnHeight),
            color = Color.Black
        )

        drawLine(
            start = Offset(0f, 0f),
            end = Offset(0f, columnHeight),
            color = Color.Black
        )
        drawLine(
            start = Offset(rowWidth, 0f),
            end = Offset(rowWidth, columnHeight),
            color = Color.Black
        )

        headerOffsets.forEach {
            val x = it.x - (padding.value / 2)
            val y = columnHeight
            drawLine(
                start = Offset(x, 0f),
                end = Offset(x, y),
                color = Color.Black
            )
 */
