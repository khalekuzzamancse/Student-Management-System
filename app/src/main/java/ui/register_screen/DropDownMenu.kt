package ui.register_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize

@Composable
fun ExposedDropdownMenu(
    items: List<String>,
    selected: String = items[0],
    onItemSelected: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selected) }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    val icon =
        if (expanded) Icons.Filled.ArrowDropUp //it requires androidx.compose.material:material-icons-extended
        else Icons.Filled.ArrowDropDown

    Box {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text("Label") },
            trailingIcon = {
                Icon(
                    icon,
                    "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            },
            readOnly = true
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            items.forEach { label ->
                DropdownMenuItem(
                    text = {
                        Text(text = label)
                    }, onClick = {
                        selectedText = label
                        expanded=false
                        onItemSelected(label)
                    })
            }
        }
    }
}


@Preview
@Composable
fun DropDownMenuPreview() {
    val list = listOf("Rangpur", "Dhaka")
    ExposedDropdownMenu(items = list, onItemSelected = {})

}