package ui.table

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.rememberTextMeasurer
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


 */
data class TableCell(
    val text: String,
    val width: Float = 0f,
    val coordinate: Offset = Offset(0f, 0f),
)

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
    val eachCellHeight: Float,
    val rows: List<Row>,

    ) {
    fun getRow(rowNo: Int) = rows[rowNo]
    fun getCell(row: Int, column: Int) = rows[row].getCell(column)
    fun getHeight(): Float {
        val numberOfRows = rows.size
        return numberOfRows * eachCellHeight
    }

    fun getWidth(): Float {

        return eachCellHeight
    }
}

val table = Table(
    eachCellHeight = 10f,
    rows = listOf(
        Row(
            listOf(
                TableCell("Roll", width = 10f),
                TableCell("Name", width = 10f),
                TableCell("Department", width = 15f)
            )
        ),
        Row(
            listOf(
                TableCell("01", width = 12f),
                TableCell("Mr Bean", width = 10f),
                TableCell("CSE", width = 15f)
            )
        ),
        Row(
            listOf(
                TableCell("02", width = 11f),
                TableCell("Dr USA", width = 10f),
                TableCell("EEE", width = 15f)
            )
        ),

        )
)


fun main() {
    println("${table.getRow(0).getWidth()}")
    println("${table.getRow(1).getWidth()}")
    println("${table.getRow(2).getWidth()}")
    println("${table.getHeight()},${table.getWidth()}")
}

@Preview
@Composable
private fun TableComposable() {
    val textMeasurer = rememberTextMeasurer()
    val padding = 20.dp.value

    val getTextWidth: (String) -> Float = {
        textMeasurer.measure(it).size.width.toFloat()
    }
    val getTextHeight: (String) -> Float = {
        textMeasurer.measure(it).size.height.toFloat()
    }

}



