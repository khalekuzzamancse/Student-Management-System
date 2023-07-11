package com.khalekuzzaman.just.cse.common_ui_element.input_field

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.unit.dp

@Composable
fun OutlinedTextInputField(
    modifier: Modifier = Modifier,
    label: String,
    onTextChanged: (String) -> Unit,
    icon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    hints: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    initialText: String = "",
    maxLine: Int = 1,

    ) {
    var text by remember { mutableStateOf(initialText) }
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
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
        visualTransformation = visualTransformation,
        maxLines = maxLine
    )

}

@Preview
@Composable
private fun NoOutlinedTextInputFieldPreview() {
    Column(modifier=Modifier.padding(8.dp)) {
        NoOutlinedTextInputField(
            hints = "Title",
            onTextChanged = {}
        )
        NoOutlinedTextInputField(
            hints = "Title",
            onTextChanged = {}
        )
    }

}
@Composable
fun NoOutlinedTextInputField(
    modifier: Modifier = Modifier,
    label: String="",
    onTextChanged: (String) -> Unit,
    icon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    hints: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    initialText: String = "",

    ) {
    var text by remember { mutableStateOf(initialText) }
    TextField(
        modifier = modifier.fillMaxWidth(),
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
        visualTransformation = visualTransformation
    )

}