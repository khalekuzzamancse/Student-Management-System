package ui.dashboard

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
/*
Problems:
I want to check,if the
 */
@Composable
fun AdminDashboard() {
    val list = listOf(
        DashboardItem("Edit Profile Request", Icons.Default.Edit),
        DashboardItem("Delete Profile Request", Icons.Default.Book),
        DashboardItem("Register  Request", Icons.Default.Book),
        DashboardItem("Add Exam", Icons.Default.Book),
        DashboardItem("Up Coming Exam", Icons.Default.Schedule),
        DashboardItem("Previous Exam", Icons.Default.Schedule),
        DashboardItem("Publish Result", Icons.Default.Schedule),
        DashboardItem("Search User", Icons.Default.VerifiedUser),
        DashboardItem("Add Course", Icons.Default.Add),
    )
    DashBoard(
        list = list,
        onDashboardItemClick = {
            Log.i("Dashboard", it)
        }
    )
}
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


@Preview(
    showSystemUi = true
)
@Composable
fun AdminDashboardAdmin() {
    AdminDashboard()
}
@Preview(
    showSystemUi = true
)
@Composable
fun StudentDashboardPreview() {
    StudentDashboard()
}
