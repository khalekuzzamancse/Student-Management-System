package com.khalekuzzaman.just.cse.text_editor.editor_ui.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatAlignCenter
import androidx.compose.material.icons.filled.FormatAlignJustify
import androidx.compose.material.icons.filled.FormatAlignLeft
import androidx.compose.material.icons.filled.FormatAlignRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    AlignmentPicker(onAlignmentPicked = {})
}

@Composable
fun AlignmentPicker(
    modifier: Modifier = Modifier,
    onAlignmentPicked: (TextAlign) -> Unit,
) {
    var show by remember {
        mutableStateOf(false)
    }
    var icon by remember {
        mutableStateOf(Icons.Default.FormatAlignLeft)
    }
    val dismissWindow: () -> Unit = {
        show = false
    }
    val showWindow: () -> Unit = {
        show = true
    }

    DropDownMenu(
        modifier = modifier,
        onDismiss = dismissWindow,
        showWindow = show,
        floatingWindowLanucher = {
            IconButton(
                icon = icon,
                onClick = {
                    showWindow()
                }
            )
        }) {
        Row() {
            IconButton(
                icon = Icons.Default.FormatAlignLeft,
                onClick = {

                    onAlignmentPicked(TextAlign.Start)
                    dismissWindow()
                })

            IconButton(
                icon = Icons.Default.FormatAlignCenter,
                onClick = {
                    onAlignmentPicked(TextAlign.Center)
                    icon = Icons.Default.FormatAlignCenter
                    dismissWindow()
                }
            )

            IconButton(
                icon = Icons.Default.FormatAlignRight,
                onClick = {
                    onAlignmentPicked(TextAlign.Right)
                    icon = Icons.Default.FormatAlignRight
                    dismissWindow()
                })

            IconButton(
                icon = Icons.Default.FormatAlignJustify,
                onClick = {
                    onAlignmentPicked(TextAlign.Justify)
                    icon = Icons.Default.FormatAlignJustify
                    dismissWindow()
                })
        }
    }

}
