package ui.course_enrollment.course_details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
fun SyllabusTablePreview() {
    val topic=CourseComponentFakeData.topicDetails01
    SyllabusTable(
        listOf(topic,topic)
    )
}

@Composable
private fun SyllabusTable(
    list: List<TopicDetails>,
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
        /*
        Table Data

         */

        list.forEach { topic ->
            TableRow(
                _1stCellContentAlignment = Alignment.TopStart,
                columnsWidth = headerTextWidth,
                _1stCellContent = {
                    UnitLearningOutCome(
                        list = topic.unitLearningOutcomes,
                        width = headerTextWidth[0]
                    )
                },
                _2ndCellContent = {
                    CourseContentCellContent(
                        items = topic.courseContent
                    )
                },
                _3rdCellContent = {
                    Text(
                        text = topic.teachingStrategies.joinToString(", ")
                    )

                },
                _4thCellContent = {
                    Text(
                        text = topic
                            .assessmentStrategies.joinToString(", ")
                    )
                }
            )
        }


    }

}

@Preview
@Composable
fun UnitLearningOutComePreview() {
    UnitLearningOutCome(
        list = listOf(
            "Item 1",
            "Item 2",
            "Item 3"
        ), width = 100.dp
    )
}

@Composable
private fun UnitLearningOutCome(
    modifier: Modifier = Modifier,
    list: List<String>,
    width: Dp,
) {
    val textMeasurer = rememberTextMeasurer()
    val density = LocalDensity.current.density
    /*
    the item has point by 'a' to 'z' instead of
    bullet point,we need max 3 character,
    so measure 3 character width
     */
    val widthInt = textMeasurer
        .measure("aa.")
        .size
        .width

    val _1stCellWidth = (widthInt / density).dp
    Column(modifier = modifier.fillMaxWidth()) {
        list.forEachIndexed { index, value ->
            val bulletPoint = ('a' + index).toString()
            val bulletPointText = "$bulletPoint. "
            Row(
                modifier = modifier
                    .height(IntrinsicSize.Min)
                    .wrapContentSize(),
            ) {
                TableCell(
                    width = _1stCellWidth,
                    content = { Text(text = bulletPointText) }
                )
                TableCell(
                    width = width - _1stCellWidth,
                    content = { Text(text = value) }
                )
            }
        }
    }


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
    _1stCellContentAlignment: Alignment = Alignment.Center,
    columnsWidth: List<Dp>,
) {

    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .wrapContentSize(),
    ) {
        TableCell(
            contentAlignment = _1stCellContentAlignment,
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
    contentAlignment: Alignment = Alignment.Center,
) {
    Box(
        contentAlignment = contentAlignment,
        modifier = modifier
            .width(width + padding)
            .fillMaxHeight()
            .border(width = 0.5.dp, Color.Black)
    ) {
        content()
    }
}