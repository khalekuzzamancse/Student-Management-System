package ui.table

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class TextualTableCell(
    val text: String,
    val width: Dp,
)

data class TextualTableRow(
    val cells: List<TextualTableCell>,
)

@Preview
@Composable
fun TextualTablePreview() {

    val data = mutableListOf<List<String>>()
    for (i in 1..40) {
        data.add(listOf("Mr Bean", "01", "Science", "4.56"))
    }
    TextualTable(
        listOf("Name", "Student Id", "Department", "Result"),
        data = data
    )
}

@Composable
fun TextualTable(
    headerText: List<String>,
    data: List<List<String>>,
) {
    val textMeasurer = rememberTextMeasurer()
    val density = LocalDensity.current.density
    val getTextWidth: (String) -> Dp = {
        val width = textMeasurer
            .measure(it)
            .size
            .width
        (width / density).dp
    }
    val headerTextWidths = remember {
        headerText.map { getTextWidth(it) }
    }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    var sum = 0f
    headerTextWidths.forEach {
        sum += it.value
    }
    val hasExtraSpace = sum.dp < screenWidth
    var extra = 0.dp
    if (hasExtraSpace) {
        extra = (screenWidth - sum.dp) / headerText.size
    }

    val headerCells = mutableListOf<TextualTableCell>()
    for (i in headerText.indices) {
        val cellWidth = headerTextWidths[i] + extra
        headerCells.add(TextualTableCell(headerText[i], cellWidth))
    }
    Column(
        Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        TextualTableRow(row = TextualTableRow(headerCells))
        LazyColumn(
            modifier = Modifier
        ) {
            items(data) {
                data.forEach { rowData ->
                    val rowCells = mutableListOf<TextualTableCell>()
                    for (i in rowData.indices) {
                        val cellWidth = headerTextWidths[i] + extra
                        rowCells.add(TextualTableCell(rowData[i], cellWidth))
                    }
                    TextualTableRow(
                        modifier = Modifier,
                        row = TextualTableRow(rowCells)
                    )
                }
            }
        }

    }

}


/*
Each cell in a row will take the maximum height of its sibling cells,
ensuring that all cells in the row have the same height
 based on the tallest cell.
 after taking the width the remaining available width will be
 divided equally among all the cell so use weight modifier
 but there is a problem with weight
 if we use weight then it can not make horizontal scrollable

 */
@Composable
fun TextualTableRow(
    modifier: Modifier = Modifier,
    row: TextualTableRow,
    padding: Dp = 8.dp,
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
    ) {
        row.cells.forEach { cell ->
            val cellWidth = cell.width + padding + padding
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(cellWidth)
                    .fillMaxHeight()
                    .border(width = 0.5.dp, Color.Black)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(),
                    text = cell.text,
                )
            }
        }
    }
}