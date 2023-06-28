package ui.dashboard

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun StudentDashboard() {
    val list = listOf(
        DashboardItem("Edit Profile", Icons.Default.Edit),
        DashboardItem("Courses", Icons.Default.Book),
        DashboardItem("Up Coming Exam", Icons.Default.Schedule),
        DashboardItem("Previous Exam", Icons.Default.Schedule),
        DashboardItem("Result", Icons.Default.Schedule),
        DashboardItem("Show Profile", Icons.Default.VerifiedUser),
    )
    DashBoard(list = list,
        onDashboardItemClick = {
            Log.i("Dashborad", it)
        })
}

@Preview
@Composable
fun StudentDashboardPreview() {

    StudentDashboard()
}
