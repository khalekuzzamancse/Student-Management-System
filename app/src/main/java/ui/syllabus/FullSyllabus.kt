package ui.syllabus

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
            .verticalScroll(rememberScrollState())
    ) {
        TopSection(
            modifier=Modifier.padding(10.dp),
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
        RecommendedBooks(books = CourseComponentFakeData.books)
    }
}