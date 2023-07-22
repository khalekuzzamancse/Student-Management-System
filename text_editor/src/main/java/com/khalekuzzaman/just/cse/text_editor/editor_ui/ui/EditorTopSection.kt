package com.khalekuzzaman.just.cse.text_editor.editor_ui.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatBold
import androidx.compose.material.icons.filled.FormatClear
import androidx.compose.material.icons.filled.FormatItalic
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.FormatStrikethrough
import androidx.compose.material.icons.filled.FormatUnderlined
import androidx.compose.material.icons.filled.Subscript
import androidx.compose.material.icons.filled.Superscript
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
        onHighLightColorPick = {  },
        onSuperscriptClick = {},
        onSubscriptClick = {},
        onFontSelected = {}
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
    onHighLightColorPick: (Color) -> Unit,
    onFontSelected: (Int) -> Unit,
    onSuperscriptClick:()->Unit,
    onSubscriptClick:()->Unit,
) {
    Surface(
        tonalElevation = tonalElevation,
        modifier = modifier.fillMaxWidth()
    ) {
            FlowRow(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
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
                IconButton(
                    icon = Icons.Default.FormatStrikethrough,
                    onClick = onLineThroughIconClick
                )

                ColorPicker(onColorPicked =onColorPicked )
                ColorHighLighterPicker(onColorPicked = onHighLightColorPick)
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
                IconButton(
                    icon = Icons.Default.Superscript,
                    onClick = onSuperscriptClick
                )
                IconButton(
                    icon = Icons.Default.Subscript,
                    onClick = onSubscriptClick
                )

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


