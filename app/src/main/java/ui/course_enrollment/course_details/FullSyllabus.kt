package ui.course_enrollment.course_details

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FullSyllabusPreview() {
    FullSyllabus(syllabus = CourseComponentFakeData.syllabus01)
}

@Composable
fun FullSyllabus(
    syllabus: Syllabus,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .verticalScroll(rememberScrollState())
    ) {
        TopSection(
            courseTitle = syllabus.courseTitle.toString(),
            courseCode = syllabus.courseCode.toString(),
            credit = syllabus.credit,
            contactHourPerWeek = syllabus.contactHoursPerWeek,
            prerequisites = syllabus.prerequisites,
            totalMarks = syllabus.markDistribution.finalExamMarks,
            finalExamMarks = syllabus.markDistribution.finalExamMarks,
            classTestMarks = syllabus.markDistribution.classTestMarks,
            classAttendanceMarks = syllabus.markDistribution.classAttendanceMarks
        )
        SyllebusTopicDetailsTable(
            syllabus.topicDetails
        )
    }
}