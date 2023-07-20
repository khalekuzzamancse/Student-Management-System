package com.khalekuzzaman.just.cse.text_editor.editor_ui.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun DropDownMenu(
    modifier: Modifier = Modifier,
    showWindow: Boolean,
    onDismiss: () -> Unit,
    windowAlignment: Alignment = Alignment.TopCenter,
    floatingWindowLanucher: @Composable () -> Unit,
    floatingWindowContent: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .wrapContentSize(windowAlignment)
    ) {
        floatingWindowLanucher()
        DropdownMenu(
            expanded = showWindow,
            onDismissRequest = onDismiss
        ) {
            floatingWindowContent()
        }
    }
}

@Composable
fun DropDownMenu(
    floatingWindowItems: List<String>,
    onDismiss: () -> Unit,
    onOptionSelected: (String) -> Unit,
    show: Boolean,
    floatingWindowLauncher: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopCenter)
    ) {
        floatingWindowLauncher()
        DropdownMenu(
            expanded = show,
            onDismissRequest = onDismiss
        ) {
            floatingWindowItems.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = { onOptionSelected(it) }
                )
            }
        }
    }
}