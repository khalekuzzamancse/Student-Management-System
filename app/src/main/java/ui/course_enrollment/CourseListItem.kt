package ui.course_enrollment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun Preview() {
    CourseListItem(
        courseTitle = "Introduction to Algorithms",
        enrolledStatus = EnrolledStatus.Pending,
        onEnrollButtonClick = {},
        onMoreButtonClick = {},
        instructorName = "Dr. Navin Garage"
    )
}
@Composable
fun CourseListItem(
    modifier: Modifier = Modifier,
    courseTitle: String,
    enrolledStatus: EnrolledStatus,
    onEnrollButtonClick: () -> Unit,
    onMoreButtonClick: () -> Unit,
    instructorName: String,
) {
    Surface(modifier = modifier,
        shadowElevation = 5.dp
    ) {
        Column(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
            Text(text = courseTitle)
            Text(text = "By $instructorName")
            Row(
                modifier = Modifier
                    .fillMaxWidth(),

                ) {
                Text(text = "Enrollment Status : $enrolledStatus")

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                TextButton(onClick = onEnrollButtonClick) {
                    Text(text = "Enroll")
                }
                TextButton(onClick = onMoreButtonClick) {
                    Text(text = "More Info")
                }
            }

        }
    }

}

enum class EnrolledStatus {
    Pending, Enrolled
}
