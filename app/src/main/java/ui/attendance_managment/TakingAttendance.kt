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
import androidx.compose.material3.Button
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

data class Attendance(
    val studentId: Int,
    val isPresent: Boolean = false,
    val imageResource: Int = R.drawable.photo,
    val previousAttendance:Int=10,
)

val attendances = listOf(
    Attendance(2301, false),
    Attendance(2302, false),
    Attendance(2303, false),
    Attendance(2304, false),
    Attendance(2305, false),
    Attendance(2306, false),
    Attendance(2307, false),
    Attendance(2308, false),
    Attendance(2309, false),
    Attendance(2310, false),
    Attendance(2311, false),
    Attendance(2312, false),
)
@Preview
@Composable
fun TakeAttendancePreview() {

    AttendanceSheet(attendances)

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
        modifier = Modifier.fillMaxWidth()
    ){
        /*
        to make the header not moving taking additional column
         */
        HeaderRow()
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
            Button(
                modifier = Modifier,
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Submit")
            }
        }
    }

}

@Composable
private fun HeaderRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .width(50.dp)
                .clip(CircleShape),
            text = "Image"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier
                .weight(1f),
            text = "Student Id"
        )
        Text(text = "Present")
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

    Row(
        modifier = modifier
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