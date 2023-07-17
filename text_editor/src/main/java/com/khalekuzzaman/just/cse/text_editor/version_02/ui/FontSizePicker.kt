package com.khalekuzzaman.just.cse.text_editor.version_02.ui
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    FontSizePicker(
        onFontSelected = {},
        shouldShowPicker = true
    )
}


@Composable
fun FontSizePicker(
    modifier: Modifier = Modifier,
    onFontSelected: (Int) -> Unit,
    shouldShowPicker: Boolean,
) {

    val list= mutableListOf<String>()
    for(i in 10..24)
        list.add("$i")

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize()
    ) {
        DropdownMenu(
            expanded = shouldShowPicker,
            onDismissRequest = {  onFontSelected(13) }
        ) {
            list.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(),
                            text = it
                        )
                    },
                    onClick = {
                        onFontSelected(it.toInt())
                    })
            }

        }
    }
}