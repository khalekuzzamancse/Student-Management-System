package ui.syllabus

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object TopSectionLabel {
    const val COURSE_TITLE = "Course Title"
    const val COURSE_CODE = "Course Code"
    const val CREDIT = "Credit"
    const val CONTACT_HOUR_PER_WEEK = "Contact Hours/Week"
    const val TOTAL_MARKS = "Total"
    const val PREREQUISITES = "Prerequisites"
    const val FINAL_EXAM_MARKS = "Final Exam"
    const val CLASS_TEST_MARKS = "Class Test"
    const val CLASS_ATTENDANCE_MARKS = "Class Attendance"

    val topSectionLabels = listOf(
        COURSE_TITLE,
        COURSE_CODE,
        CREDIT,
        CONTACT_HOUR_PER_WEEK,
        TOTAL_MARKS,
        PREREQUISITES,
        FINAL_EXAM_MARKS,
        CLASS_TEST_MARKS,
        CLASS_ATTENDANCE_MARKS
    )
}

@Preview
@Composable
private fun SyllabusTopSectionPreview() {
    TopSection(
        courseTitle = "Introduction to Algorithms",
        courseCode = "CSE 2101",
        credit = 3.0f,
        contactHourPerWeek = 3.0f,
        totalMarks = 100,
        prerequisites = listOf(CourseCode.Science0901, CourseCode.Arts0901),
        finalExamMarks = 72,
        classTestMarks = 20,
        classAttendanceMarks = 8
    )

}

@Composable
fun TopSection(
    modifier: Modifier=Modifier,
    courseTitle: String,
    courseCode: String,
    credit: Float,
    contactHourPerWeek: Float,
    totalMarks: Int,
    prerequisites: List<CourseCode>,
    finalExamMarks: Int,
    classTestMarks: Int,
    classAttendanceMarks: Int,
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
    val labelMaxWidth = TopSectionLabel.topSectionLabels.maxOf { getTextWidth(it) }
        Column(modifier = modifier
            .fillMaxWidth()
        //find out why it can not make horizonal scroll able
        ) {
            EachRow(
                label = TopSectionLabel.COURSE_TITLE,
                value = courseTitle,
                labelWidth = labelMaxWidth
            )
            EachRow(
                label = TopSectionLabel.COURSE_CODE,
                value = courseCode,
                labelWidth = labelMaxWidth
            )
            EachRow(
                label = TopSectionLabel.CREDIT,
                value = credit.toString(),
                labelWidth = labelMaxWidth
            )
            EachRow(
                label = TopSectionLabel.CONTACT_HOUR_PER_WEEK,
                value = "$contactHourPerWeek hour",
                labelWidth = labelMaxWidth
            )
            EachRow(
                label = TopSectionLabel.TOTAL_MARKS,
                value = "$totalMarks Marks",
                labelWidth = labelMaxWidth
            )
            EachRow(
                label = TopSectionLabel.PREREQUISITES,
                value = prerequisites.joinToString(", "),
                labelWidth = labelMaxWidth
            )
            //Marks distribution
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Marks Distribution",
                style =MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            //Marks distribution End
            EachRow(
                label = TopSectionLabel.FINAL_EXAM_MARKS,
                value = "$finalExamMarks Marks",
                labelWidth = labelMaxWidth
            )
            EachRow(
                label = TopSectionLabel.CLASS_TEST_MARKS,
                value = "$classTestMarks Marks",
                labelWidth = labelMaxWidth
            )
            EachRow(
                label = TopSectionLabel.CLASS_ATTENDANCE_MARKS,
                value = "$classAttendanceMarks Marks",
                labelWidth = labelMaxWidth
            )
        }


}

/*
If the corresponding label data is not fit on the screen
in the single line then we make the value only scrollable
that is why we have to use:
     .horizontalScroll(rememberScrollState())
 */
@Composable
private fun EachRow(label: String, value: String, labelWidth: Dp) {
    Row(
        modifier = Modifier
            .wrapContentSize(),
    ) {
        Text(modifier = Modifier.width(labelWidth), text = label)
        Spacer(Modifier.width(4.dp))
        Text(text = ":")
        Spacer(Modifier.width(4.dp))
        Text(
            modifier = Modifier
                .weight(1f),
            text = value
        )
    }
}
