package ui.register_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bloodtype
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Man
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview


/*
Form
 */
@Composable
fun TextualForm() {

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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
        FormInputField(
            label = "Blood Group",
            onTextChanged = {},
            icon = Icons.Default.Bloodtype,
        )
        FormInputField(
            label = "Gender",
            onTextChanged = {},
            icon = Icons.Default.Man,
        )
        //use visual transfer for date of birth
        //use the date picker for date of birth
        FormInputField(
            label = "Date of Birth",
            onTextChanged = {},
            icon = Icons.Default.DateRange,
            keyboardType = KeyboardType.Number,
            visualTransformation = DateVisualTransformer,
        )
        //as a student
        FormInputField(
            label = "Father Name",
            onTextChanged = {},
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
        //For teacher
        FormInputField(
            label = "NID",
            onTextChanged = {},
            icon = Icons.Default.Info,
            keyboardType = KeyboardType.Phone,
        )
        ExposedDropdownMenu(
            items = districtList,
            onItemSelected = {},
            label = "Department"
        )


    }


}

@Preview
@Composable
fun FormPreview() {
    TextualForm()
}

