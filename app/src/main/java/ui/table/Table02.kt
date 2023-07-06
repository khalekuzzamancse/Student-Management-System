package ui.table

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun Table02() {
    val headerText = listOf("Roll", "Name", "Department", "Result")
    val textMeasurer = rememberTextMeasurer()
    val density = LocalDensity.current.density
    val getTextWidth: (String) -> Dp = {
        val width = textMeasurer
            .measure(it)
            .size
            .width
        (width / density).dp
    }
    val headerTextMaxWidth = headerText.maxOf { getTextWidth(it) }
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxSize()
        .horizontalScroll(rememberScrollState())
        .verticalScroll(rememberScrollState())
    ) {
        Text(text = "Result  Published")
        EachRow(
            columnWidth = headerTextMaxWidth,
            columnPadding = 20.dp,
            cellText = headerText
        )
        val list= listOf("01", "Mr Bean", "EEE", "4.98")
        for(i in  1..100){
            EachRow(
                columnWidth = headerTextMaxWidth,
                columnPadding = 20.dp,
                cellText = list
            )
        }
    }

}

@Composable
fun EachRow(
    modifier: Modifier = Modifier,
    columnWidth: Dp,
    columnPadding: Dp,
    cellText: List<String>,
) {
    Row(
        modifier = Modifier
            .wrapContentSize()
    ) {
        cellText.forEachIndexed { _, text ->
            Text(
                modifier = modifier
                    .width(columnWidth + columnPadding + columnPadding)
                    .border(width = 0.50.dp, color = Color.Black),
                text = text,
                textAlign = TextAlign.Center
            )
        }

    }


}