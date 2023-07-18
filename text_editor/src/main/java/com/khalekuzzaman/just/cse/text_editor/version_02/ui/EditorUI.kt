package com.khalekuzzaman.just.cse.text_editor.version_02.ui

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.text_editor.version_02.EditorVisualTransformer
import com.khalekuzzaman.just.cse.text_editor.version_02.Formatter
import com.khalekuzzaman.just.cse.text_editor.version_02.FormattedIndicesManager
import com.khalekuzzaman.just.cse.text_editor.version_02.Formatters
import com.khalekuzzaman.just.cse.text_editor.version_02.SingleCharacterChangeListener
import com.khalekuzzaman.just.cse.text_editor.version_02.SingleCharacterChangeUtils
import java.util.TreeMap


@Preview
@Composable
fun TextEditorVersion01() {
    var textFieldText by remember { mutableStateOf(TextFieldValue("012345678910111213")) }
    var previousText by remember { mutableStateOf("") }
    var map by remember { mutableStateOf(TreeMap<Int, Set<Formatter>>()) }
    var pickedAlignment by remember { mutableStateOf(TextAlign.Start) }

    val visualTransformation = EditorVisualTransformer()
        .createTextFormatter(formatterMap = map)
    /*

     */
    val addFormatter: (Formatter) -> Unit = {
        val manager = FormattedIndicesManager(map)
        val start = textFieldText.selection.start
        val end = textFieldText.selection.end
        val list = start.until(end).toList()
        map = if (manager.doesExits(list, it)) {
            manager.removeFormat(list, it).indices
        } else {
            FormattedIndicesManager(map)
                .addFormatter(list, it)
                .indices
        }

    }
    val clearFormat: () -> Unit = {
        val start = textFieldText.selection.start
        val end = textFieldText.selection.end
        val list = start.until(end).toList()
        map = FormattedIndicesManager(map)
            .removeFormat(list)
            .indices
    }

    /*

     */
    Column(modifier = Modifier.padding(8.dp)) {
        TextEditorTopSection(
            onBoldIconClick = { addFormatter(Formatters.Bold) },
            onItalicIconClick = { addFormatter(Formatters.Italic) },
            onUnderLineIconClick = { addFormatter(Formatters.UnderLine) },
            onColorPicked = { addFormatter(Formatters.Colored(it)) },
            onLineThroughIconClick = { addFormatter(Formatters.LineThrough) },
            onBulletListClick = {},
            onNumberListClick = {},
            onFormatClearClick = clearFormat,
            onAlignmentPicked = { pickedAlignment = it },
            onHighLightColorPick = { addFormatter(Formatters.HighLight(it)) },
            onFontSelected = { addFormatter(Formatters.FontSize(it)) }
        )


        BasicTextField(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth()
                .height(300.dp)
                .border(width = 1.dp, color = Color.Black)
                .padding(16.dp),
            value = textFieldText,
            onValueChange = { currentText ->
                textFieldText = currentText
                val utils = SingleCharacterChangeUtils(
                    currentText = currentText.text,
                    previousText = previousText
                )
                map = SingleCharacterChangeListener(utils, map).onTextChange()
                debugPrint(map)
                previousText = currentText.text

            },
            visualTransformation = visualTransformation,
            textStyle = LocalTextStyle.current.copy(textAlign = pickedAlignment)

        )

    }
}

fun debugPrint(map: TreeMap<Int, Set<Formatter>>) {
    for ((key, value) in map) {
        val names = value.map { it.javaClass.simpleName }
        Log.i("FORMATTERS:", "$key:${names.joinToString(",", "[", "]")}")
    }

}