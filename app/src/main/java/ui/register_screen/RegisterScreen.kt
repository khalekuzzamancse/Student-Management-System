package ui.register_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bloodtype
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Man
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview


/*
FromInputFiled
 */

@Composable
fun FormInputField(
    label: String,
    onTextChanged: (String) -> Unit,
    icon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    hints: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,

    ) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        label = { Text(text = label) },
        leadingIcon = {
            if (icon != null) {
                Icon(imageVector = icon, contentDescription = null)
            }
        },
        value = text,
        onValueChange = {
            text = it
            onTextChanged(it)
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        placeholder = {
            Text(text = hints)
        },
        visualTransformation = DateVisualTransformer
    )

}

@Preview
@Composable
fun FormEachRowPreview() {
    FormInputField(
        label = "Date of birth",
        icon = Icons.Default.DateRange,
        onTextChanged = {},
        keyboardType = KeyboardType.Number,
    )

}


/*
Form
 */
@Composable
fun TextualForm() {

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        FormInputField(
            label = "Name",
            icon = Icons.Default.Person,
            onTextChanged = {}
        )
        FormInputField(
            label = "Email",
            icon = Icons.Default.Email,
            onTextChanged = {},
            keyboardType = KeyboardType.Email
        )
        FormInputField(
            label = "Phone",
            icon = Icons.Default.Phone,
            onTextChanged = {},
            keyboardType = KeyboardType.Phone
        )
        FormInputField(
            label = "Blood Group",
            icon = Icons.Default.Bloodtype,
            onTextChanged = {},
        )
        FormInputField(
            label = "Gender",
            icon = Icons.Default.Man,
            onTextChanged = {},
        )
        //use visual transfer for date of birth
        //use the date picker for date of birth
        FormInputField(
            label = "Date of Birth",
            icon = Icons.Default.DateRange,
            onTextChanged = {},
            keyboardType = KeyboardType.Number
        )
        //as a student
        FormInputField(
            label = "Father Name",
            icon = Icons.Default.Person,
            onTextChanged = {},
        )
        FormInputField(
            label = "Mother Name",
            icon = Icons.Default.Person,
            onTextChanged = {},
        )
        FormInputField(
            label = "Father Phone No",
            icon = Icons.Default.Phone,
            onTextChanged = {},
            keyboardType = KeyboardType.Phone
        )
        FormInputField(
            label = "Mother Phone No",
            icon = Icons.Default.Phone,
            onTextChanged = {},
            keyboardType = KeyboardType.Phone
        )


    }


}

@Preview
@Composable
fun FormPreview() {
    TextualForm()
}

