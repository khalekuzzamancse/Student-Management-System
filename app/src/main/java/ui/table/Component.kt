package ui.table

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
Table:
Requirements:
*The displayed in single line;width=wrap content=
*Since there is single line so height of all cell is same

!---------!------!-----------------!
!Name     !Roll  !Dept             !
!---------!------!-----------------!
!Md USA   !01    !Computer  Science!
!---------!------!-----------------!

consider each TableCell with the properties:
    1.text(string)
    2.width
        use as default parameters because while creating the table data
        we do not know the exact size so later need to changed.
        use immutable so need to use default value
    3.Coordinate(as Offset)
        use as default parameters because while creating the table data
        we do not know the exact Coordinate
    height need not to  store,all cell height are the same.

Let each row  is a tuple,each tuple has column,
the TableTuple has the properties:
    1.A fixed size List of TableCell

Padding:p:
    because of padding each cell top(ordinate) will get x amount extra space
    and left

Finding the text height and width
    Using text measurer

 */
data class TableCell(
    val text: String,
) {
    var width: Float = 0f
        get() {
            return field + (2 * padding)
        }

    companion object {
        var height = 0f
        var padding = 20.dp.value
    }


}

data class Row(
    var cells: List<TableCell>,
)

data class Table(
    val rows: List<Row>,
    ) {

    private var columnsTopLeftX = mutableMapOf<Int, Float>()
    private var rowsTopLeftY = mutableMapOf<Int, Float>()
    var verticalLinesTopLeftCoordinates =
        mutableMapOf<Int, Pair<Offset, Offset>>()
    var horizontalLinesTopLeftCoordinates =
        mutableMapOf<Int, Pair<Offset, Offset>>()

    private fun getWidth(): Float {
        var width = 0f
        for (row in 0 until getNumbersOfColumn()) {
            val eachCellWidth = getColumnMaxWidth(row)
            width += eachCellWidth
        }
        return width
    }

    private fun getTableHeight() =
        getNumbersOfRow() * (TableCell.height)

    private fun getColumnMaxWidth(rowNo: Int): Float {
        val result =rows.map { row ->
            row.cells[rowNo]
        }.maxOf { it.width }
        return result
    }

    private fun calculateRowTopLeftY() {
        //since row height are the same
        for (row in 0..getNumbersOfRow()) {
            rowsTopLeftY[row] = row * TableCell.height
        }
    }

    private fun calculateColumnTopLeftX() {

        val columnsTopLeftX = mutableMapOf<Int, Float>()
        rows.first().cells.size
        columnsTopLeftX[0] = 0f
        for (column in 1..getNumbersOfColumn()) {
            val previousColumnWidth = columnsTopLeftX[column - 1] ?: 0f
            columnsTopLeftX[column] =
                previousColumnWidth + getColumnMaxWidth(column - 1)
        }

        this.columnsTopLeftX = columnsTopLeftX
    }

    fun calculateCoordinate() {
        calculateColumnTopLeftX()
        calculateRowTopLeftY()
    }

    fun getCellTopLeftCoordinate(row: Int, col: Int): Offset {
        //move the text right to padding amount
        var x = columnsTopLeftX[col] ?: 0f
        x += TableCell.padding //later fix this
        return Offset(
            x = x,
            y = rowsTopLeftY[row] ?: 0f
        )
    }


    fun calculateVerticalLinesCoordinates() {
        val endPointY = getTableHeight()
        val verticalLinesTopLeftCoordinates =
            mutableMapOf<Int, Pair<Offset, Offset>>()
        verticalLinesTopLeftCoordinates[0] = Pair(
            Offset(0f, 0f),
            Offset(0f, endPointY)
        )
        for (col in 0..getNumbersOfColumn()) {
            val startPointX = columnsTopLeftX[col] ?: 0f
            verticalLinesTopLeftCoordinates[col] = Pair(
                Offset(startPointX, 0f),
                Offset(startPointX, endPointY)
            )
        }
        this.verticalLinesTopLeftCoordinates = verticalLinesTopLeftCoordinates

    }


    fun calculateHorizontalLinesCoordinates() {
        val endPointX = getWidth()
        val horizontalLinesTopLeftCoordinates =
            mutableMapOf<Int, Pair<Offset, Offset>>()
        horizontalLinesTopLeftCoordinates[0] = Pair(
            Offset(0f, 0f),
            Offset(endPointX, 0f)
        )
        for (row in 1..getNumbersOfRow()) {
            rowsTopLeftY[row] ?: 0f
            val y = (row * TableCell.height)
            horizontalLinesTopLeftCoordinates[row] = Pair(
                Offset(0f, y),
                Offset(endPointX, y)
            )
        }
        this.horizontalLinesTopLeftCoordinates = horizontalLinesTopLeftCoordinates

    }

    private fun getNumbersOfColumn() = rows.first().cells.size
    private fun getNumbersOfRow() = rows.size


}


class TableTestData {
    fun getData():Table{
        val row= Row(
            listOf(
                TableCell("01"),
                TableCell("Mr Bean"),
                TableCell("CSE")
            )
        )

        val rows= mutableListOf<Row>(
            Row(
                listOf(
                    TableCell("Roll"),
                    TableCell("Name"),
                    TableCell("Department")
                )))
        for(i in 1..40){
            rows.add(row)
        }
        return Table(rows)

    }

}



