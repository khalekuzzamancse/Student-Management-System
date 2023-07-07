package com.khalekuzzaman.just.cse.studentmanagementsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.khalekuzzaman.just.cse.studentmanagementsystem.ui.theme.StudentManagementSystemTheme
import ui.syllabus.CourseComponentFakeData
import ui.syllabus.FullSyllabus


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

