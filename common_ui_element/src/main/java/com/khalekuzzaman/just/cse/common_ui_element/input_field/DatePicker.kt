package com.khalekuzzaman.just.cse.common_ui_element.input_field

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getDate(milliseconds: Long): String {
    val date = Date(milliseconds)
    val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return sdf.format(date)
}


@Preview
@Composable
fun DatePickerPreview() {
    CustomDatePicker(
        "Date of Birth",
        icon = Icons.Default.DateRange

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    label: String,
    icon: ImageVector,
) {
    var text by remember {
        mutableStateOf("")
    }
    var openDialog by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .fillMaxWidth(),
        label = { Text(label) },
        leadingIcon = {
            Icon(
                icon,
                null
            )
        },
        trailingIcon = {
            Icon(
                Icons.Default.Edit,
                null,
                modifier=Modifier.clickable{
                    text=""
                    openDialog=true
                }
            )
        },
      readOnly = true
    )


    if (openDialog) {
        val datePickerState = rememberDatePickerState()

        //
        text= datePickerState.selectedDateMillis?.let { getDate(it) }.toString()
        //
        val confirmEnabled =
            remember { derivedStateOf { datePickerState.selectedDateMillis != null } }
        DatePickerDialog(
            onDismissRequest = {
                openDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(
                showModeToggle = false,
                state = datePickerState
            )
        }
    }
}