package ui.course_enrollment.course_details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
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

    val headerTextWidth = headerText.map { getTextWidth(it) }
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxHeight()
            .horizontalScroll(rememberScrollState())
    ) {
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
            }
        )
        TableRow(
            columnsWidth = headerTextWidth,
            _1stCellContent = {

            },
            _2ndCellContent = {
                CourseContentCellContent(
                    listOf("Item 1", "Item 2", "Item 3")
                )
            },
            _3rdCellContent = {
                EnumObjectsToList(
                    list = CourseComponentFakeData.teachingStrategies01
                )
            },
            _4thCellContent = {
                EnumObjectsToList(
                    list = CourseComponentFakeData.assessmentStrategies01
                )
            }
        )

    }

}

@Composable
fun <T> EnumObjectsToList(
    modifier: Modifier = Modifier,
    list: List<T>,
) {
    Text(modifier = modifier, text = list.joinToString(", "))
}

@Composable
fun CourseContentCellContent(items: List<String>) {
    Column {
        items.forEach { item ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color.Black, shape = CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = item)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
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
    padding: Dp = 16.dp,
) {
    val getCellModifier: (Dp) -> Modifier = { width ->
        Modifier
            .width(width + padding)
            .fillMaxHeight()
            .border(width = 0.5.dp, Color.Black)

    }
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .wrapContentSize(),
    ) {
        TableCell(
            width = columnsWidth[0],
            content = _1stCellContent
        )
        TableCell(
            width = columnsWidth[1],
            content = _2ndCellContent
        )
        TableCell(
            width = columnsWidth[2],
            content = _3rdCellContent
        )
        TableCell(
            width = columnsWidth[3],
            content = _4thCellContent
        )

    }

}

@Composable
fun TableCell(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    width: Dp, padding: Dp = 16.dp,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(width + padding)
            .fillMaxHeight()
            .border(width = 0.5.dp, Color.Black)
    ) {
        content()
    }
}