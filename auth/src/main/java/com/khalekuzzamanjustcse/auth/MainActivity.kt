package com.khalekuzzamanjustcse.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.khalekuzzamanjustcse.auth.ui.register.RegistrationForm
import com.khalekuzzamanjustcse.auth.ui.theme.StudentManagementSystemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentManagementSystemTheme {
                RegistrationForm()
            }
        }
    }
}

@Preview
@Composable
fun P() {
    StudentManagementSystemTheme {
        RegistrationForm()
    }

}
