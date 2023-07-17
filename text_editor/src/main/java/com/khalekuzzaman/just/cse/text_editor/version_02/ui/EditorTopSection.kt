package com.khalekuzzaman.just.cse.text_editor.version_02.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.FormatAlignLeft
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material.icons.filled.FormatClear
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
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
        onColorPicked = {},
        onLineThroughIconClick = {},
        onBulletListClick = { /*TODO*/ },
        onNumberListClick = { /*TODO*/ },
        onFormatClearClick = {},
        onAlignmentPicked = {},
        onFontSelected = {},
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TextEditorTopSection(
    modifier: Modifier = Modifier,
    onBoldIconClick: () -> Unit,
    onItalicIconClick: () -> Unit,
    onUnderLineIconClick: () -> Unit,
    onColorPicked: (Color) -> Unit,
    onLineThroughIconClick: () -> Unit,
    onBulletListClick: () -> Unit,
    onNumberListClick: () -> Unit,
    onFormatClearClick: () -> Unit,
    onAlignmentPicked: (TextAlign) -> Unit,
    tonalElevation: Dp = 5.dp,
    onFontSelected: (Int) -> Unit,
) {
    Surface(
        tonalElevation = tonalElevation,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier

        ) {
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())

            ) {

                FontTypePicker()
                FontSizePicker(
                    onFontSelected =onFontSelected ,
                )
                IconButton(
                    icon = Icons.Default.FormatBold,
                    onClick = onBoldIconClick
                )
                IconButton(
                    icon = Icons.Default.FormatItalic,
                    onClick = onItalicIconClick
                )
                IconButton(
                    icon = Icons.Default.FormatUnderlined,
                    onClick = onUnderLineIconClick
                )
            }


            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {

                IconButton(
                    icon = Icons.Default.FormatStrikethrough,
                    onClick = onLineThroughIconClick
                )

                ColorPicker(onColorPicked =onColorPicked )
                AlignmentPicker(onAlignmentPicked = onAlignmentPicked)

                IconButton(
                    icon = Icons.Default.FormatListBulleted,
                    onClick = onBulletListClick
                )

                IconButton(
                    icon = Icons.Default.FormatListNumbered,
                    onClick = onNumberListClick
                )
                IconButton(
                    icon = Icons.Default.FormatClear,
                    onClick = onFormatClearClick
                )

            }

        }

    }
}



@Composable
 fun IconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: ImageVector,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}


