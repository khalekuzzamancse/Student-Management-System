package com.khalekuzzaman.just.cse.text_editor.version_02.editor_ui

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun TextEditorVersion01() {
    var textFieldText by remember { mutableStateOf(TextFieldValue("012345678910111213")) }
    var previousText by remember { mutableStateOf("") }
    var map by remember { mutableStateOf(TreeMap<Int, Set<Formatter>>()) }


    val visualTransformation = EditorVisualTransformer()
        .createTextFormatter(formatterMap = map)


    var showColorPicker by remember { mutableStateOf(false) }
    var pickedColor by remember { mutableStateOf(Color.Blue) }




    /*

     */
    val boldText: () -> Unit = {
        map = FormatterHolder(map)
            .addFormatter(textFieldText.selection, Formatters.Bold)
            .formattedIndices
    }
    val italicText: () -> Unit = {
        map = FormatterHolder(map)
            .addFormatter(textFieldText.selection, Formatters.Italic)
            .formattedIndices
    }
    val COLORText: () -> Unit = {
        map = FormatterHolder(map)
            .addFormatter(textFieldText.selection, Formatters.RedColor)
            .formattedIndices
        showColorPicker = true

    }
    val underLineText: () -> Unit = {
        map = FormatterHolder(map)
            .addFormatter(textFieldText.selection, Formatters.UnderLine)
            .formattedIndices
    }
    val lineThrough: () -> Unit = {
        map = FormatterHolder(map)
            .addFormatter(textFieldText.selection, Formatters.LineThrough)
            .formattedIndices
    }
    /*

     */
    Column(modifier = Modifier.padding(8.dp)) {
        TextEditorTopSection(
            onBulletListClick = {},
            onNumberListClick = {},
            onFormatClearClick = {},
            onBoldIconClick = boldText,
            onItalicIconClick = italicText,
            onUnderLineIconClick = underLineText,
            onTextColorChangeIconClick = COLORText,
            onLineThroughIconClick = lineThrough,
        )
        if (showColorPicker) {
            ColorPicker(
                modifier = Modifier.wrapContentSize(Alignment.TopCenter),
                onColorPicked = {
                    showColorPicker = false
                    pickedColor = it
                },
                shouldShowPicker = true
            )

        }
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

    }
}