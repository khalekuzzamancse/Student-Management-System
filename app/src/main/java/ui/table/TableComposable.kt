package ui.table

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
Problem with the solution:
drawText will throw run time exception when it get coordinate
out of screen.
so this is fit for only non scrollable screen
later in-sha-allah we have to fix this

Need to know:
    How to make canvas scrollable
    How to drawText() outside of device screen
    How to draw text outside of canvas(if possible)
    Try to use sub compose or lazy layout
 */
@Preview
@Composable
private fun Preview() {
    TableComposable()
}

@Composable
fun TableComposable() {
    val textMeasurer = rememberTextMeasurer()
    val table = TableTestData().getData()

    val getTextWidth: (String) -> Float = {
        textMeasurer
            .measure(it, style = TextStyle.Default.copy(fontSize = 15.sp))
            .size
            .width.toFloat()
    }
    val getTextHeight: (String) -> Float = {
        textMeasurer.measure(it).size.height.toFloat()
    }
    TableCell.height = getTextHeight(table.rows.first().cells.first().text)

    table.rows.map { row ->
        row.cells.map { cell ->
            cell.width = getTextWidth(cell.text)
        }
        row
    }
    table.calculateCoordinate()
    table.calculateVerticalLinesCoordinates()
    table.calculateHorizontalLinesCoordinates()

    Canvas(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()

    ) {
        table.rows.forEachIndexed { rowNo, row ->
            row.cells.forEachIndexed { colNo, cell ->
                    drawText(
                        topLeft = table.getCellTopLeftCoordinate(rowNo, colNo),
                        textMeasurer = textMeasurer,
                        text = cell.text,
                    )

            }

            //drawing vertical lines
            table.verticalLinesTopLeftCoordinates.forEach {
                //move each text horizontally as the padding
                drawLine(
                    start = it.value.first,
                    end = it.value.second,
                    color = Color.Black,
                    strokeWidth = 2.dp.value
                )
            }
            //drawing horizontal line
            table.horizontalLinesTopLeftCoordinates.forEach {
                drawLine(
                    start = it.value.first,
                    end = it.value.second,
                    color = Color.Black,
                    strokeWidth = 2.dp.value
                )
            }

        }
    }


}

