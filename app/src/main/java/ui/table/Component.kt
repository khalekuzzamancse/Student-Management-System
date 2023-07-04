package ui.table

import android.util.Log
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

Padding:x:
    because of padding each cell top(ordinate) will get x amount extra space
    and left


 */
data class TableCell(
    val text: String,
    var width: Float = 0f,
    var coordinate: Offset = Offset(0f, 0f),
) {
    companion object {
        var height = 0f
    }
}

data class Row(
    var cells: List<TableCell>,
) {
    fun getCell(columnNo: Int) = cells[columnNo]
    fun updateCell(tableCell: TableCell, columnNo: Int) {
        val updatedCells = cells.toMutableList()
        updatedCells[columnNo] = tableCell
        cells = updatedCells
    }

    fun updateCoordinate(coordinate: Offset, columnNo: Int) {
        val updatedCells = cells.toMutableList()
        val cell = updatedCells[columnNo]
        updatedCells[columnNo] = cell.copy(coordinate = coordinate)
        cells = updatedCells
    }

    fun updateCoordinate(ordinate: Float, columnNo: Int) {
        val updatedCells = cells.toMutableList()
        val cell = updatedCells[columnNo]
        val coordinate = cell.coordinate
        updatedCells[columnNo] = cell.copy(coordinate = coordinate.copy(y = ordinate))
        cells = updatedCells
    }

    fun updateCoordinate(columnNo: Int, abscissa: Float) {
        val updatedCells = cells.toMutableList()
        val cell = updatedCells[columnNo]
        val coordinate = cell.coordinate
        updatedCells[columnNo] = cell.copy(coordinate = coordinate.copy(x = abscissa))
        cells = updatedCells
    }

    fun getWidth(): Float {
        var totalWidth = 0f
        cells.forEach {
            totalWidth += it.width
        }
        return totalWidth
    }

}

data class Table(
    val rows: List<Row>,
    val cellPadding: Float = 0f,

    ) {
    var columnsTopLeftX = mutableMapOf<Int, Float>()
    var rowsTopLeftY = mutableMapOf<Int, Float>()
    fun getRow(rowNo: Int) = rows[rowNo]
    fun getCell(row: Int, column: Int) = rows[row].getCell(column)
    fun getHeight(): Float {
        val numberOfRows = rows.size
        return numberOfRows * TableCell.height
    }

    fun getWidth(): Float {
        return rows.maxOf { it.getWidth() }
    }

    fun getColumnMaxWidth(rowNo: Int): Float {
        val result = table.rows.map { row ->
            row.cells[rowNo]
        }.maxOf { it.width }
        return result
    }

    fun calculateCoordinate() {
        val columnsTopLeftX = mutableMapOf<Int, Float>()
        val totalColumns = rows.first().cells.size
        columnsTopLeftX[0] = 0f
        for (column in 1 until totalColumns) {
            val tmp = columnsTopLeftX[column - 1] ?: 0f
            columnsTopLeftX[column] = tmp + getColumnMaxWidth(column - 1)
        }
        this.columnsTopLeftX = columnsTopLeftX
        val rowsTopLeftY = mutableMapOf<Int, Float>()
        val totalRow = rows.size
        rowsTopLeftY[0] = 0f
        for (row in 1 until totalRow) {
            val tmp = rowsTopLeftY[row - 1] ?: 0f
            rowsTopLeftY[row] = tmp + TableCell.height
        }
        this.rowsTopLeftY = rowsTopLeftY
    }

    fun getCellTopLeftCoordinate(row: Int, col: Int):Offset {
        val offset = Offset(x = columnsTopLeftX[col] ?: 0f, y = rowsTopLeftY[row] ?: 0f)
        println("TABLE_CELL::CellCoor $offset")
        return offset
    }


}

val table = Table(
    rows = listOf(
        Row(
            listOf(
                TableCell("Roll"),
                TableCell("Name"),
                TableCell("Department")
            )
        ),
        Row(
            listOf(
                TableCell("01"),
                TableCell("Mr Bean"),
                TableCell("CSE")
            )
        ),
        Row(
            listOf(
                TableCell("02"),
                TableCell("Dr USA"),
                TableCell("EEE")
            )
        ),
        Row(
            listOf(
                TableCell("03"),
                TableCell("Salman Ali Khan"),
                TableCell("Mathematics")
            )
        ),

        )
)



@Preview
@Composable
private fun TableComposable() {
    val textMeasurer = rememberTextMeasurer()

    val getTextWidth: (String) -> Float = {
        textMeasurer.measure(it).size.width.toFloat()
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
    Canvas(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        table.rows.forEachIndexed { rowNo, row ->
            row.cells.forEachIndexed { colNo, cell ->
                drawText(
                    topLeft = table.getCellTopLeftCoordinate(rowNo,colNo),
                    textMeasurer = textMeasurer,
                    text = cell.text,
                )
            }
        }

    }
    }


