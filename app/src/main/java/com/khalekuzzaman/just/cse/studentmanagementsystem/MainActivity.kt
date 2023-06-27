package com.khalekuzzaman.just.cse.studentmanagementsystem

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.khalekuzzaman.just.cse.studentmanagementsystem.ui.theme.StudentManagementSystemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentManagementSystemTheme {
                GoogleMap(
                    onMyLocationClick = {
                        Log.i("MapClicked:onMyLocationClick", "Loaded")
                    },
                    onMyLocationButtonClick = {
                        Log.i("MapClicked:onMyLocationClick", "LocationIcon")
                        true
                    },
                    properties = MapProperties(
                        isMyLocationEnabled = true
                    ),
                    )
            }
        }
    }
}

