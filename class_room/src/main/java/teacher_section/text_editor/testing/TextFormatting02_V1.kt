package teacher_section.text_editor.testing

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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import teacher_section.text_editor.text_formatting.CharacterFormatter
import teacher_section.text_editor.text_formatting.FormattedIndicesUpdater
import teacher_section.text_editor.text_formatting.Formatter
import teacher_section.text_editor.text_formatting.TextEditorVisualTransformer


@Preview
@Composable
private fun TextFormatter_V01() {
    var textFieldText by remember { mutableStateOf(TextFieldValue("012345678910111213")) }
    var previousText by remember { mutableStateOf("") }
    var boldIndices by remember { mutableStateOf(mutableListOf<Int>()) }
    var colorIndices by remember { mutableStateOf(mutableListOf<Int>()) }
    var italicIndices by remember { mutableStateOf(mutableListOf<Int>()) }

    val formatters = listOf(
        Formatter(
            indices = boldIndices,
            formatter = CharacterFormatter.BoldFormatter
        ),
        Formatter(
            indices = colorIndices,
            formatter = CharacterFormatter.RedColorFormatter
        ),
        Formatter(
            indices = italicIndices,
            formatter = CharacterFormatter.ItalicFormatter
        ),
    )

    val formatter = TextEditorVisualTransformer().createTextFormatter(
        formatters = formatters
    )


    Column(modifier = Modifier.padding(8.dp)) {
        TextField(
            value = textFieldText,
            onValueChange = { currentText ->
                textFieldText = currentText

                boldIndices = FormattedIndicesUpdater.updateFormattedIndices(
                    currentText = currentText.text,
                    previousText = previousText,
                    formattedIndices = boldIndices
                ).toMutableList()

                colorIndices = FormattedIndicesUpdater.updateFormattedIndices(
                    currentText = currentText.text,
                    previousText = previousText,
                    formattedIndices = colorIndices
                ).toMutableList()
                italicIndices = FormattedIndicesUpdater.updateFormattedIndices(
                    currentText = currentText.text,
                    previousText = previousText,
                    formattedIndices = italicIndices
                ).toMutableList()

                previousText = currentText.text

            },
            visualTransformation = formatter
        )
        Spacer(modifier = Modifier.height(100.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                boldIndices =FormattedIndicesUpdater.updateFormattedIndicesWithSelection(
                    selectedTextRange = textFieldText.selection,
                    formattedIndices = boldIndices
                ).toMutableList()

            }) {
                Text(text = "Bold")
            }

            Button(onClick = {
                colorIndices = FormattedIndicesUpdater.updateFormattedIndicesWithSelection(
                    selectedTextRange = textFieldText.selection,
                    formattedIndices = colorIndices
                ).toMutableList()
            }) {
                Text(text = "Red Color")
            }
            Button(onClick = {
                italicIndices = FormattedIndicesUpdater.updateFormattedIndicesWithSelection(
                    selectedTextRange = textFieldText.selection,
                    formattedIndices = italicIndices
                ).toMutableList()
            }) {
                Text(text = "Italic")
            }
        }

    }

}



