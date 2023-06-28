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
    )
    DashBoard(
        list = list,
        onDashboardItemClick = {
            Log.i("Dashborad",it)
        }
    )
}

@Preview
@Composable
fun AdminDashboardAdmin() {
    AdminDashboard()
}