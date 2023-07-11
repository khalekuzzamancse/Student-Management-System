package com.khalekuzzaman.just.cse.class_room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.khalekuzzaman.just.cse.class_room.ui.theme.StudentManagementSystemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentManagementSystemTheme {

            }
        }
    }
}

