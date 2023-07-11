package com.khalekuzzaman.just.cse.common_ui_element.drop_down_menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview


@Composable
@Preview(showBackground = true)
fun DropDownDemo() {
    TextualDropDownMenu(
        menuItems = listOf("Bangla", "English", "Physics"),
        onMenuItemClick = {}
    )
}

@Composable
fun TextualDropDownMenu(
    modifier: Modifier = Modifier,
    onMenuItemClick: (String) -> Unit,
    menuItems: List<String>,
) {
    LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            menuItems.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = { onMenuItemClick(it) }
                )
            }

        }
    }
}