package com.khalekuzzaman.just.cse.text_editor.editor_ui.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
private fun Preview() {
    FontTypePicker()
}


@Composable
fun FontTypePicker(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var show by remember {
        mutableStateOf(false)
    }
    var selected by remember { mutableStateOf("Roboto") }
    var trailingIcon by remember { mutableStateOf(Icons.Default.ArrowDropDown) }
    val dismissWindow: () -> Unit = {
        show = false
        trailingIcon = Icons.Default.ArrowDropDown
    }
    val showWindow: () -> Unit = {
        show = true
        trailingIcon = Icons.Default.ArrowDropUp
    }
    Column(modifier=modifier) {
        DropDownMenu(
            floatingWindowLauncher = {
                TextField(

                    modifier = Modifier.width(130.dp),
                    readOnly = true,
                    value = selected,
                    onValueChange = {},
                    singleLine = true,
                    trailingIcon = {
                        Icon(
                            modifier = Modifier.clickable {
                                showWindow()
                            },
                            imageVector = trailingIcon,
                            contentDescription = null
                        )
                    },


                    )
            },
            floatingWindowItems = listOf("Roboto", "Arial", "Mono", "QuickSand"),
            show = show,
            onOptionSelected = {
                selected = it
                dismissWindow()
                Toast.makeText(
                    context, "Under construction!!",
                    Toast.LENGTH_SHORT
                ).show()
            },
            onDismiss = dismissWindow

        )
    }

}