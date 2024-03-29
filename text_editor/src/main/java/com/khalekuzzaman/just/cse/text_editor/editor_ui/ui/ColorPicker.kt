package com.khalekuzzaman.just.cse.text_editor.editor_ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatColorFill
import androidx.compose.material.icons.filled.FormatColorText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


 val coloredBoxes = listOf(
    Color.Blue, Color.Green, Color.Red,
    Color.Black, Color.Yellow, Color.White,
    Color.DarkGray, Color.Gray, Color.LightGray,
)



@Preview
@Composable
private fun Preview() {
    ColorPicker(onColorPicked = {})
}

@Composable
fun ColorHighLighterPicker(
    modifier: Modifier = Modifier,
    onColorPicked: (Color) -> Unit,
) {
    var show by remember {
        mutableStateOf(false)
    }
    val dismissWindow: () -> Unit = {
        show = false
    }
    val showWindow: () -> Unit = {
        show = true
    }

    DropDownMenu(
        modifier = modifier,
        showWindow = show,
        onDismiss = dismissWindow,
        floatingWindowLanucher = {
            IconButton(
                icon = Icons.Default.FormatColorFill,
                onClick = {
                    showWindow()
                }
            )
        }) {
        ColorPlate(
            onColorChooseBoxClick = {
                onColorPicked(it)
                dismissWindow()
            },
            list = coloredBoxes
        )
    }


}

@Composable
fun ColorPicker(
    modifier: Modifier = Modifier,
    onColorPicked: (Color) -> Unit,
) {
    var show by remember {
        mutableStateOf(false)
    }
    val dismissWindow: () -> Unit = {
        show = false
    }
    val showWindow: () -> Unit = {
        show = true
    }

    DropDownMenu(
        modifier = modifier,
        showWindow = show,
        onDismiss = dismissWindow,
        floatingWindowLanucher = {
            IconButton(
                icon = Icons.Default.FormatColorText,
                onClick = {
                    showWindow()
                }
            )
        }) {
        ColorPlate(
            onColorChooseBoxClick = {
                onColorPicked(it)
                dismissWindow()
            },
            list = coloredBoxes
        )
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