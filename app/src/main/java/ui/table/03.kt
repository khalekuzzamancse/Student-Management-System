package ui.table

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/*
This code show basically how to calculate the coordiante
this code have problem,such as the when text content is more then
it will overlap then content or overflow the content
to solve the overflow problem
we have redefine it
 */
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
    val getPreviousRowUpNeighbourTextHeight: (Int) -> Float = {
        getTextHeight(rows[it - 1].first().text)
    }
    val getPreviousRowUpNeighbourCoordinate: (Int, Int) -> Offset = { index, column ->
        rows[index - 1][column].coordinate
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
            val previousRowHeight = getPreviousRowUpNeighbourTextHeight(i)
            val neighbourCoordinate = getPreviousRowUpNeighbourCoordinate(i, index)
            val ordinate = neighbourCoordinate.y + previousRowHeight
            val abscissa = neighbourCoordinate.x
            rows[i][index] = rows[i][index].copy(coordinate = Offset(abscissa, ordinate))
        }
    }



    Canvas(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        rows.forEach { row ->
            row.forEachIndexed { index, content ->
                val headerWidth = getTextWidth(rows.first()[index].text)
                val thisCellHeight = getTextHeight(content.text)
                drawText(
                    topLeft = content.coordinate,
                    textMeasurer = textMeasurer,
                    text = content.text,
                    size = Size(width = headerWidth, height = thisCellHeight),
                    overflow = TextOverflow.Ellipsis,
                )
            }

        }

    }
}
