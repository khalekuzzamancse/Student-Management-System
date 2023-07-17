package com.khalekuzzaman.just.cse.text_editor.version_02.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatAlignCenter
import androidx.compose.material.icons.filled.FormatAlignJustify
import androidx.compose.material.icons.filled.FormatAlignLeft
import androidx.compose.material.icons.filled.FormatAlignRight
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AlignmentPicker(
    modifier: Modifier = Modifier,
    onAlignmentPicked: (TextAlign) -> Unit,
    shouldShowPicker: Boolean,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize()
    ) {
        DropdownMenu(
            expanded = shouldShowPicker,
            onDismissRequest = { }
        ) {
            Row() {
                IconButton(onClick = { onAlignmentPicked(TextAlign.Start) }) {
                    Icon(
                        imageVector = Icons.Default.FormatAlignLeft,
                        contentDescription = null
                    )
                }
                IconButton(onClick = { onAlignmentPicked(TextAlign.Center) }) {
                    Icon(
                        imageVector = Icons.Default.FormatAlignCenter,
                        contentDescription = null
                    )
                }
                IconButton(onClick = { onAlignmentPicked(TextAlign.Right) }) {
                    Icon(
                        imageVector = Icons.Default.FormatAlignRight,
                        contentDescription = null
                    )
                }
                IconButton(onClick = { onAlignmentPicked(TextAlign.Justify) }) {
                    Icon(
                        imageVector = Icons.Default.FormatAlignJustify,
                        contentDescription = null
                    )
                }

            }

        }
    }
}