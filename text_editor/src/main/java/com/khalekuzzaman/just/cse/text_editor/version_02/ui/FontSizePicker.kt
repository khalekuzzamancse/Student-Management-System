package com.khalekuzzaman.just.cse.text_editor.version_02.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun Preview() {
    FontSizePicker(
        onFontSelected = {},
    )
}


@Composable
fun FontSizePicker(
    modifier: Modifier = Modifier,
    onFontSelected: (Int) -> Unit,
) {
    var selected by remember { mutableIntStateOf(15) }
    var show by remember {
        mutableStateOf(false)
    }
    val dismissWindow: () -> Unit = {
        show = false
    }
    val showWindow: () -> Unit = {
        show = true
    }
    val increaseFontSize: () -> Unit = {
        selected += 2
       selected= selected.coerceIn(10,24)
    }
    val decreaseFontSize: () -> Unit = {
        selected -= 2
        selected= selected.coerceIn(10,24)
    }
    //

    val list = mutableListOf<String>()
    for (i in 15..24)
        list.add("$i")

    TextualDropDownMenu(
        options = list,
        shouldExpanded = show,
        onOptionSelected = {
            selected = it.toInt()
            onFontSelected(selected)
            dismissWindow()
        },
        onDismiss = {
            onFontSelected(13)
        }

    ) {
        Row(modifier=modifier) {
            IconButton(
                icon = Icons.Default.Remove,
                onClick = {
                    decreaseFontSize()
                    onFontSelected(selected)
                }
            )
            Text(
                modifier = Modifier
                    .padding(4.dp)
                    .size(40.dp)
                    .border(width = 1.dp, Color.Black)
                    .clickable {
                        showWindow()
                    }
                    .fillMaxSize()
                    .wrapContentSize(),
                text = selected.toString())

            IconButton(
                icon = Icons.Default.Add,
                onClick = {
                    increaseFontSize()
                    onFontSelected(selected)
                }
            )
        }

    }
}

