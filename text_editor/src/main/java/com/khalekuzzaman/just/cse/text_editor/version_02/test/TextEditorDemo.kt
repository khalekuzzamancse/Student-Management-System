package com.khalekuzzaman.just.cse.text_editor.version_02.test


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.text_editor.text_ordering.BulletManager
import com.khalekuzzaman.just.cse.text_editor.text_formatting.EditorVisualTransformer
import com.khalekuzzaman.just.cse.text_editor.text_formatting.Formatter
import com.khalekuzzaman.just.cse.text_editor.text_formatting.FormattedIndicesManager
import com.khalekuzzaman.just.cse.text_editor.text_formatting.Formatters
import com.khalekuzzaman.just.cse.text_editor.text_formatting.SingleCharacterChangeListener
import com.khalekuzzaman.just.cse.text_editor.text_formatting.SingleCharacterChangeUtils
import java.util.TreeMap


@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
private fun TextEditorDemo() {
    var textFieldText by remember { mutableStateOf(TextFieldValue("012345678910111213")) }
    var previousText by remember { mutableStateOf("") }
    var map by remember { mutableStateOf(TreeMap<Int, Set<Formatter>>()) }


    val visualTransformation = EditorVisualTransformer()
        .createTextFormatter(formatterMap = map)


    Column(modifier = Modifier.padding(8.dp)) {
        TextField(
            value = textFieldText,
            onValueChange = { currentText ->
                textFieldText = currentText
                val utils = SingleCharacterChangeUtils(
                    currentText = currentText.text,
                    previousText = previousText
                )
                map = SingleCharacterChangeListener(utils, map).onTextChange()


                //
                previousText = currentText.text

            },
            visualTransformation = visualTransformation
        )
        Spacer(modifier = Modifier.height(100.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                val start = textFieldText.selection.start
                val end = textFieldText.selection.end
                val list = start.until(end).toList()
                map = FormattedIndicesManager(map)
                    .addFormatter(list, Formatters.Bold)
                    .indices
            }) {
                Text(text = "Bold")
            }

            Button(onClick = {
                val start = textFieldText.selection.start
                val end = textFieldText.selection.end
                val list = start.until(end).toList()
                map = FormattedIndicesManager(map)
                    .addFormatter(list, Formatters.RedColor)
                    .indices
            }) {
                Text(text = "Red Color")
            }
            Button(onClick = {
                val start = textFieldText.selection.start
                val end = textFieldText.selection.end
                val list = start.until(end).toList()
                map = FormattedIndicesManager(map)
                    .addFormatter(list, Formatters.Italic)
                    .indices
            }) {
                Text(text = "Italic")
            }
            Button(onClick = {
                val start = textFieldText.selection.start
                val end = textFieldText.selection.end
                BulletManager(text = textFieldText.text, start = start, end)
                  //  .insertBullets { text, index ->
                    .insertBullet { text, index ->
                        textFieldText = TextFieldValue(text)
                        previousText = text
                        map = FormattedIndicesManager(map).shiftFormattedIndices(index, 1).indices
                    }

            }) {
                Text(text = "Bullet Add")
            }
            Button(onClick = {
                val start = textFieldText.selection.start
                val end = textFieldText.selection.end
                BulletManager(text = textFieldText.text, start = start, end)
                    .removeBullets{ text, index ->
                        textFieldText = TextFieldValue(text)
                        previousText = text
                        map = FormattedIndicesManager(map).shiftFormattedIndices(index, -1).indices
                    }

            }) {
                Text(text = "Bullet Remove")
            }
        }

    }

}



