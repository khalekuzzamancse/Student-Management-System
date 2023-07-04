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
    val padding = 20.dp // Example padding value

    val rows = mutableListOf(
        mutableListOf(
            TableContent("Roll"),
            TableContent("Name"),
            TableContent("Department"),
            TableContent("Section")
        ),
        mutableListOf(
            TableContent("01"),
            TableContent("Abul"),
            TableContent("Arts"),
            TableContent("A")
        ),
        mutableListOf(
            TableContent("Roll"),
            TableContent("Name"),
            TableContent("Department"),
            TableContent("Section")
        )
    )


    val getTextWidth: (String) -> Float = {
        textMeasurer.measure(it).size.width.toFloat()
    }
    val getTextHeight: (String) -> Float = {
        textMeasurer.measure(it).size.height.toFloat()
    }


    var previousX = 0f
    val y = 0f


    rows.first().forEachIndexed { index, content ->
        val textWidth = getTextWidth(content.text)
        val textHeight = getTextHeight(content.text)
        val x = previousX + padding.value // Add padding to the x-coordinate
        previousX += textWidth + padding.value // Add padding to the next x-coordinate
        rows.first()[index] =
            rows.first()[index].copy(
                width = textWidth,
                height = textHeight,
                coordinate = Offset(x, y)
            )
    }


    for (i in 1 until rows.size) {
        rows[i].forEachIndexed { index, _ ->
            val previousRowHeight = getTextHeight(rows[i - 1].first().text)
            //      val previousRowHeight = rows[i - 1].first().height
            //this line is not working,find it why not work
            val currentCellUpCellCoordinate = rows[i - 1][index].coordinate
            val coordinate =
                currentCellUpCellCoordinate.copy(y = currentCellUpCellCoordinate.y + previousRowHeight)
            rows[i][index] = rows[i][index].copy(coordinate = coordinate)
        }
    }


    Canvas(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        rows.forEach { row ->
            row.forEach { content ->
                drawText(
                    topLeft = content.coordinate,
                    textMeasurer = textMeasurer,
                    text = content.text
                )
            }

        }

    }
}