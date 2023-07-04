package ui.table

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


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
    val dataRow1 = mutableListOf(
        TableContent("01"),
        TableContent("Abul"),
        TableContent("Arts"),
        TableContent("A")
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


    dataRow1.forEachIndexed{index, content ->
        val previousRowHeight=headerContents.first().height
        val currentCellUpCellCoordinate=headerContents[index].coordinate
        val coordinate=currentCellUpCellCoordinate.copy(y=currentCellUpCellCoordinate.y+previousRowHeight)
        dataRow1[index]=dataRow1[index].copy(coordinate = coordinate)
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
        dataRow1.forEach { content ->
            drawText(
                topLeft = content.coordinate,
                textMeasurer = textMeasurer,
                text = content.text
            )
        }
    }
}