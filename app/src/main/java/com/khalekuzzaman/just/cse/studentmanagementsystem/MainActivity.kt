package com.khalekuzzaman.just.cse.studentmanagementsystem

import FakeUserData
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.khalekuzzaman.just.cse.studentmanagementsystem.ui.theme.StudentManagementSystemTheme
import ui.course_enrollment.course_details.CourseComponentFakeData
import ui.course_enrollment.course_details.FullSyllabus
import ui.user_info.UserList


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentManagementSystemTheme {
                FullSyllabus(syllabus = CourseComponentFakeData.syllabus01)

            }
        }
    }
}

