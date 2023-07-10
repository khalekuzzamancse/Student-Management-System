package ui.course_enrollment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class CourseItem(
    val courseTitle: String,
    val enrolledStatus: EnrolledStatus,
    val instructorName: String,
)

@Preview
@Composable
fun CourseListPreview() {
    val list= mutableListOf<CourseItem>()
    for(i in 1..20)
        list.add(
            CourseItem(
                courseTitle = "Introduction to Algorithms",
                enrolledStatus = EnrolledStatus.Pending,
                instructorName = "Dr Navin Garage"
            )
        )
    CourseList(courses = list)
}

@Composable
fun CourseList(courses: List<CourseItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        courses.forEach {
            CourseListItem(
                modifier=Modifier.padding(8.dp),
                courseTitle = it.courseTitle,
                enrolledStatus = it.enrolledStatus,
                onEnrollButtonClick = {},
                onMoreButtonClick = {},
                instructorName = it.instructorName
            )
        }

    }

}