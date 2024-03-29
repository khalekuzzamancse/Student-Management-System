package com.khalekuzzamanjustcse.auth.ui.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bloodtype
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Man
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import ui.register_screen.CustomDatePicker
import ui.register_screen.ExposedDropdownMenu
import ui.register_screen.FormInputField
import ui.register_screen.MapLocationInputField
import ui.register_screen.MyButton


/*
Form
 */

object FormData {
    val registrationType = listOf("Student", "Teacher")
    val districts = listOf(
        "Dhaka",
        "Khulna",
    )
    val subDistrict = listOf(
        "USA",
        "America",
        "England",
    )
    val dept = listOf(
        "Bengali",
        "English",
        "Math"
    )
    val genderList = listOf(
        "Male",
        "Female",
        "Other"
    )
    val bloodGroupList = listOf(
        "A",
        "A+",
        "O-",
        "AB"
    )

}

@Composable
fun RegistrationForm(
    modifier: Modifier = Modifier,
) {
    var registerAsTeacher by remember {
        mutableStateOf(false)
    }
    var registerAsStudent by remember {
        mutableStateOf(true)
    }
    var showGoogleMap by remember {
        mutableStateOf(false)
    }
    var showDrawingBoard by remember {
        mutableStateOf(false)
    }
    var location by remember { mutableStateOf("") }
    Box(modifier = modifier) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(
                        rememberScrollState()
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FormInputField(
                    label = "Name",
                    onTextChanged = {},
                    icon = Icons.Default.Person,
                )
                FormInputField(
                    label = "Email",
                    onTextChanged = {},
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                )
                FormInputField(
                    label = "Phone",
                    onTextChanged = {},
                    icon = Icons.Default.Phone,
                    keyboardType = KeyboardType.Phone,
                )
                ExposedDropdownMenu(
                    items = FormData.bloodGroupList,
                    onItemSelected = {
                    },
                    label = "Blood Group",
                    icon = Icons.Default.Bloodtype
                )
                ExposedDropdownMenu(
                    items = FormData.genderList,
                    onItemSelected = {
                    },
                    label = "Gender",
                    icon = Icons.Default.Man
                )
                //017
                //use visual transfer for date of birth
                //use the date picker for date of birth
                CustomDatePicker(
                    label = "Date of Birth",
                    icon = Icons.Default.DateRange,
                )
                ExposedDropdownMenu(
                    items = FormData.registrationType,
                    onItemSelected = {
                        registerAsStudent = it == FormData.registrationType[0]
                        registerAsTeacher = it == FormData.registrationType[1]
                    },
                    label = "Register As",
                    icon = Icons.Default.AppRegistration
                )

                if (registerAsStudent) {
                    FormInputField(
                        label = "Father Name",
                        onTextChanged = {

                        },
                        icon = Icons.Default.Person,
                    )
                    FormInputField(
                        label = "Mother Name",
                        onTextChanged = {},
                        icon = Icons.Default.Person,
                    )
                    FormInputField(
                        label = "Father Phone No",
                        onTextChanged = {},
                        icon = Icons.Default.Phone,
                        keyboardType = KeyboardType.Phone,
                    )
                    FormInputField(
                        label = "Mother Phone No",
                        onTextChanged = {},
                        icon = Icons.Default.Phone,
                        keyboardType = KeyboardType.Phone,
                    )
                }

                if (registerAsTeacher) {
                    FormInputField(
                        label = "NID",
                        onTextChanged = {},
                        icon = Icons.Default.Info,
                        keyboardType = KeyboardType.Phone,
                    )
                    ExposedDropdownMenu(
                        items = FormData.dept,
                        onItemSelected = {},
                        label = "Department",
                        icon = Icons.Default.Book
                    )
                }

                ExposedDropdownMenu(
                    items = FormData.districts,
                    onItemSelected = {},
                    label = "District",
                    icon = Icons.Default.LocationCity,
                )
                ExposedDropdownMenu(
                    items = FormData.subDistrict,
                    onItemSelected = {},
                    label = "SubDistrict",
                    icon = Icons.Default.LocationCity,
                )
                MapLocationInputField(
                    label = "Location",
                    text = location,
                    onMapIconClick = {
                        showGoogleMap = true
                    },
                    icon = Icons.Default.LocationCity
                )
                Button(
                    onClick = { showDrawingBoard = true }) {
                    Icon(
                        imageVector = Icons.Default.Upload,
                        contentDescription = null
                    )
                    Text("Signature")
                }


                MyButton(
                    onClick = { /*TODO*/ },
                    label = "Register"
                )
            }
            //Google map will be displayed

        if (showGoogleMap) {
            GoogleMapView(
                onMapDismiss = {
                    showGoogleMap = false
                },
                onPlaceSelected = {
                    location = "${it.latitude} , ${it.longitude}"
                }
            )
        }
        if(showDrawingBoard){
//            WhiteBoard(modifier=Modifier.matchParentSize(),onDismissButtonClick = {
//                showDrawingBoard=false
//            })
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxScope.GoogleMapView(
    modifier: Modifier = Modifier,
    onMapDismiss: () -> Unit,
    onPlaceSelected: (LatLng) -> Unit,
) {
    Column(modifier = modifier.matchParentSize()) {
        TopAppBar(
            title = {},
            navigationIcon = {
                Icon(
                    modifier = Modifier.clickable { onMapDismiss() },
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            },
            actions = {}
        )
        GoogleMap(
            modifier = Modifier.fillMaxWidth(),
            onMapClick = onPlaceSelected
        )
    }

}

@Preview
@Composable
fun FormPreview() {
    RegistrationForm()
}

