package ui.attendance_managment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.studentmanagementsystem.R
import ui.course_enrollment.CheckboxItem

data class Attendance(
    val studentId: Int,
    val isPresent: Boolean = false,
    val imageResource: Int = R.drawable.photo,
)

@Preview
@Composable
fun TakeAttendancePreview() {

    val list = listOf(
        Attendance(2301, false),
        Attendance(2302, false),
        Attendance(2303, false),
    )
    AttendanceSheet(list)

}

@Composable
fun AttendanceSheet(studentsId: List<Attendance>) {

    var students by remember {
        mutableStateOf(studentsId)
    }
    val updateList: (Int) -> Unit = { studentId ->
        val updatedList = students.map { student ->
            if (student.studentId == studentId) {
                student.copy(isPresent = !student.isPresent)
            } else {
                student
            }
        }
        students = updatedList.toMutableList()
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        students.forEach { student ->
            EachStudent(
                isChecked = student.isPresent,
                label = student.studentId.toString(),
                imageResource = student.imageResource,
                onCheckChanged = { updateList(student.studentId) }
            )
        }
    }
}


@Composable
private fun EachStudent(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    imageResource: Int,
    onCheckChanged: () -> Unit,
    label: String,
) {

    Row(modifier = modifier
        .padding(8.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(imageResource),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier
                .weight(1f),
            text = label
        )
        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                onCheckChanged()
            }
        )
    }

}