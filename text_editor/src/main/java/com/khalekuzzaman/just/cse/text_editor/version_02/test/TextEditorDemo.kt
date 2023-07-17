package com.khalekuzzaman.just.cse.text_editor.version_02.test


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.khalekuzzaman.just.cse.text_editor.version_02.EditorVisualTransformer
import com.khalekuzzaman.just.cse.text_editor.version_02.Formatter
import com.khalekuzzaman.just.cse.text_editor.version_02.FormatterHolder
import com.khalekuzzaman.just.cse.text_editor.version_02.Formatters
import com.khalekuzzaman.just.cse.text_editor.version_02.SingleCharacterChangeListener
import com.khalekuzzaman.just.cse.text_editor.version_02.SingleCharacterChangeUtils
import java.util.TreeMap


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
                   currentText= currentText.text,
                    previousText = previousText)
                map = SingleCharacterChangeListener(utils, map).onTextChange()
                for ((key, value) in map) {
                    val names = value.map { it.javaClass.simpleName }
                    Log.i("FORMATTERS:","$key:${names.joinToString(",", "[", "]")}")
                }

                //
                previousText = currentText.text

            },
            visualTransformation = visualTransformation
        )
        Spacer(modifier = Modifier.height(100.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                map = FormatterHolder(map)
                    .addFormatter(textFieldText.selection, Formatters.Bold)
                    .formattedIndices
            }) {
                Text(text = "Bold")
            }

            Button(onClick = {
                map = FormatterHolder(map)
                    .addFormatter(textFieldText.selection, Formatters.RedColor)
                    .formattedIndices
            }) {
                Text(text = "Red Color")
            }
            Button(onClick = {
                map = FormatterHolder(map)
                    .addFormatter(textFieldText.selection, Formatters.Italic)
                    .formattedIndices
            }) {
                Text(text = "Italic")
            }
        }

    }

}


