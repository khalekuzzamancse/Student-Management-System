package ui.table

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Preview
@Composable
private fun TableRowPreview() {

}
@Composable
private fun TextualTableRow(
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
            TableCell(cellWidth = cellWidth) {
                Text(text = cell.text)
            }
        }
    }
}

@Composable
private fun RowScope.TableCell(
    modifier: Modifier = Modifier,
    cellWidth: Dp,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .align(Alignment.CenterVertically)
            .width(cellWidth)
            .fillMaxHeight()
            .border(width = 0.5.dp, Color.Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(),
        ) {
            content()
        }
    }
}