package com.khalekuzzaman.just.cse.text_editor.version_02.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.FormatAlignLeft
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material.icons.filled.FormatClear
import androidx.compose.material.icons.filled.FormatColorText
import androidx.compose.material.icons.filled.FormatItalic
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.FormatStrikethrough
import androidx.compose.material.icons.filled.FormatUnderlined
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        onFormatClearClick = {},
        onTextAlignIconClick = {},
        onFontSizeClick = {},
        onFontSizePlusIconClick = {},
        onFontSizeMinusIconClick = {},
        selectedTextSize =10
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
    onTextAlignIconClick: () -> Unit,
    tonalElevation: Dp = 5.dp,
    onFontSizeClick: () -> Unit,
    onFontSizePlusIconClick: () -> Unit,
    onFontSizeMinusIconClick: () -> Unit,
    selectedTextSize: Int,
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
            Row() {
                IconButton(onClick = onFontSizeMinusIconClick) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = null
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(40.dp)
                        .border(width = 1.dp, Color.Black),
                ) {
                    Text(
                        modifier = Modifier
                            .clickable {
                                onFontSizeClick()
                            }
                            .fillMaxSize()
                            .wrapContentSize(),
                        text = selectedTextSize.toString())
                }
                IconButton(onClick = onFontSizePlusIconClick) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
            }

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
            IconButton(onClick = onUnderLineIconClick) {
                Icon(
                    imageVector = Icons.Default.FormatUnderlined,
                    contentDescription = null
                )
            }
            IconButton(onClick = onLineThroughIconClick) {
                Icon(
                    imageVector = Icons.Default.FormatStrikethrough,
                    contentDescription = null
                )
            }
            IconButton(onClick = onTextColorChangeIconClick) {
                Icon(
                    imageVector = Icons.Default.FormatColorText,
                    contentDescription = null
                )
            }
            IconButton(onClick = onTextAlignIconClick) {
                Row() {
                    Icon(
                        imageVector = Icons.Default.FormatAlignLeft,
                        contentDescription = null
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
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



