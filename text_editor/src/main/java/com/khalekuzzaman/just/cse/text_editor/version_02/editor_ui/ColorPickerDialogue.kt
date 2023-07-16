package com.khalekuzzaman.just.cse.text_editor.version_02.editor_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


private val coloredBoxes = listOf(
    Color.Blue, Color.Green, Color.Red,
    Color.Black, Color.Yellow, Color.White,
    Color.DarkGray, Color.Gray, Color.LightGray,
)


@Composable
fun ColorPicker(
    modifier: Modifier=Modifier,
    onColorPicked: (Color) -> Unit,
    shouldShowPicker: Boolean,
    ) {
    LocalContext.current
    Box(modifier = modifier
        .fillMaxWidth()
        .wrapContentSize()) {
        DropdownMenu(
            expanded = shouldShowPicker,
            onDismissRequest = { onColorPicked(Color.Unspecified) }
        ) {
            ColorPlate(
                onColorChooseBoxClick = {
                    onColorPicked(it)
                },
                list = coloredBoxes
            )
        }
    }
}

@Composable
 fun ColorPlate(
    onColorChooseBoxClick: (Color) -> Unit,
    list: List<Color>,
) {
    Column() {
        val chunks = list.chunked(3)
        chunks.forEach { row ->
            Row() {
                row.forEach {
                    ColorChooserBox(
                        onColorChooseBoxClick = { onColorChooseBoxClick(it) },
                        color = it
                    )
                }
            }

        }
    }
}


@Composable
private fun ColorChooserBox(
    modifier: Modifier = Modifier,
    onColorChooseBoxClick: () -> Unit,
    color: Color,
) {
    Box(
        modifier = modifier
            .padding(2.dp)
            .size(24.dp)
            .clip(CircleShape)
            .background(color)
            .clickable {
                onColorChooseBoxClick()
            }
    )

}