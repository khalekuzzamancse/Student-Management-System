package ui.register_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize


/*
FromInputFiled
 */

@Composable
fun FormInputField(
    modifier: Modifier = Modifier,
    label: String,
    onTextChanged: (String) -> Unit,
    icon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    hints: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,

    ) {
    var text by remember { mutableStateOf("") }
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
        visualTransformation = visualTransformation
    )

}

@Composable
fun ReadOnlyInputField(
    modifier: Modifier = Modifier,
    label: String,
    onTextChanged: (String) -> Unit,
    icon: ImageVector? = null,
    hints: String = "",
    initialText: String,
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
        readOnly = true,
        placeholder = {
            Text(text = hints)
        },
    )

}

@Preview
@Composable
fun FormEachRowPreview() {
    FormInputField(
        label = "Date of birth",
        onTextChanged = {},
        icon = Icons.Default.DateRange,
        keyboardType = KeyboardType.Number,
        modifier = Modifier,
    )

}


@Composable
fun ExposedDropdownMenu(
    modifier: Modifier = Modifier,
    items: List<String>,
    selected: String = items[0],
    onItemSelected: (String) -> Unit,
    label: String,
    icon: ImageVector? = null,
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val trailingIcon =
        if (expanded) Icons.Filled.ArrowDropUp
        else Icons.Filled.ArrowDropDown
    val closeMenu = { expanded = false }
    val flipExpanded = { expanded = !expanded }
    var text by remember {
        mutableStateOf("")
    }
    Box(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = text,
            onValueChange = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(label) },
            leadingIcon = {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )
                }
            },
            trailingIcon = {
                Icon(
                    trailingIcon,
                    null,
                    Modifier.clickable { flipExpanded() })
            },
            readOnly = true
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = closeMenu,
            modifier = Modifier
                .width(with(LocalDensity.current)
                { textFieldSize.width.toDp() })
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(text = item)
                    }, onClick = {
                        text = item
                        closeMenu()
                        onItemSelected(item)
                    })
            }
        }
    }
}


@Preview
@Composable
fun DropDownMenuPreview() {
    val list = listOf("America", "Dhaka")
    ExposedDropdownMenu(
        items = list, onItemSelected = {},
        label = "label",
        icon = null
    )

}