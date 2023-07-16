package com.khalekuzzaman.just.cse.text_editor.version_02.editor_ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material.icons.filled.FormatClear
import androidx.compose.material.icons.filled.FormatColorText
import androidx.compose.material.icons.filled.FormatItalic
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.FormatStrikethrough
import androidx.compose.material.icons.filled.FormatUnderlined
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun Preview() {
    TextEditorTopSection(
        onBoldIconClick = {},
        onItalicIconClick = {},
        onUnderLineIconClick = {},
        onTextColorChangeIconClick = {},
        onLineThroughIconClick = {},
        onBulletListClick = { /*TODO*/ },
        onNumberListClick = { /*TODO*/ },
        onFormatClearClick = {}
    )
}

@Composable
fun TextEditorTopSection(
    modifier: Modifier = Modifier,
    onBoldIconClick: () -> Unit,
    onItalicIconClick: () -> Unit,
    onUnderLineIconClick: () -> Unit,
    onTextColorChangeIconClick: () -> Unit,
    onLineThroughIconClick: () -> Unit,
    onBulletListClick: () -> Unit,
    onNumberListClick: () -> Unit,
    onFormatClearClick: () -> Unit,
    tonalElevation: Dp = 5.dp,
) {
    Surface(
        tonalElevation = tonalElevation,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = onBoldIconClick) {
                Icon(
                    imageVector = Icons.Default.FormatBold,
                    contentDescription = null
                )
            }
            IconButton(onClick = onItalicIconClick) {
                Icon(
                    imageVector = Icons.Default.FormatItalic,
                    contentDescription = null
                )
            }
            IconButton(onClick =onUnderLineIconClick) {
                Icon(
                    imageVector = Icons.Default.FormatUnderlined,
                    contentDescription = null
                )
            }
            IconButton(onClick = onTextColorChangeIconClick) {
                Icon(
                    imageVector = Icons.Default.FormatColorText,
                    contentDescription = null
                )
            }
            IconButton(onClick = onLineThroughIconClick) {
                Icon(
                    imageVector = Icons.Default.FormatStrikethrough,
                    contentDescription = null
                )
            }

            IconButton(onClick = onBulletListClick) {
                Icon(
                    imageVector = Icons.Default.FormatListBulleted,
                    contentDescription = null
                )
            }
            IconButton(onClick = onNumberListClick) {
                Icon(
                    imageVector = Icons.Default.FormatListNumbered,
                    contentDescription = null
                )
            }
            IconButton(onClick = onFormatClearClick) {
                Icon(
                    imageVector = Icons.Default.FormatClear,
                    contentDescription = null
                )
            }
        }
    }
}



