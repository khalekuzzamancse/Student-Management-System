package ui.course_enrollment.course_details

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
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

/*
We will use fixed column width because the text can be
multi lines
 */

@Preview
@Composable
private fun TableRowPreview() {
    val textMeasurer = rememberTextMeasurer()
    val density = LocalDensity.current.density
    val getTextWidth: (String) -> Dp = {
        val width = textMeasurer
            .measure(it)
            .size
            .width
        (width / density).dp
    }

    val headerText = listOf(
        "Unit Learning Outcome",
        "Course Content",
        "Teaching Strategy",
        "Assessment Strategy"
    )
    val headerTextWidth = headerText.map{ getTextWidth(it) }
    Column(modifier=Modifier.padding(10.dp).fillMaxHeight()){
        TableRow(
            columnsWidth = headerTextWidth,
            _1stCellContent = {
                Text(text = headerText[0])
            },
            _2ndCellContent = {
                Text(text = headerText[1])
            },
            _3rdCellContent = {
                Text(text = headerText[2])
            },
            _4thCellContent = {
                Text(text = headerText[3])
            })
    }


}

@Composable
private fun TableRow(
    modifier: Modifier = Modifier,
    _1stCellContent: @Composable () -> Unit,
    _2ndCellContent: @Composable () -> Unit,
    _3rdCellContent: @Composable () -> Unit,
    _4thCellContent: @Composable () -> Unit,
    columnsWidth: List<Dp>,
    padding:Dp=16.dp
) {
    val getCellModifier: (Dp) -> Modifier = { width ->
        Modifier
            .width(width)
            .fillMaxHeight()
            .border(width = 0.5.dp, Color.Black)
            .padding(padding)
    }
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .wrapContentSize()
            .horizontalScroll(rememberScrollState())
    ) {
        Box(modifier = getCellModifier(columnsWidth[0])) { _1stCellContent() }
        Box(modifier = getCellModifier(columnsWidth[1])) { _2ndCellContent() }
        Box(modifier = getCellModifier(columnsWidth[2])) { _3rdCellContent() }
        Box(modifier = getCellModifier(columnsWidth[3])) { _4thCellContent() }
    }


}