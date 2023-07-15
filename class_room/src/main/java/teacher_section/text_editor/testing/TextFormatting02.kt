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
import teacher_section.text_editor.CharacterFormatter
import teacher_section.text_editor.Formatter
import teacher_section.text_editor.TextChangeWatcher
import teacher_section.text_editor.TextEditorVisualTransformer
import teacher_section.text_editor.updateIndicesOnCharacterRemoval

@Preview
@Composable
private fun Preview() {
    var boldIndices by remember { mutableStateOf(mutableListOf<Int>()) }
    var colorIndices by remember { mutableStateOf(mutableListOf<Int>()) }
    var italicIndices by remember { mutableStateOf(mutableListOf<Int>()) }

    var textFieldText by remember { mutableStateOf(TextFieldValue("0123456789")) }
    var previousText by remember { mutableStateOf("") }
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
                boldIndices = updateFormattedIndices(
                    currentText = currentText.text,
                    previousText = previousText,
                    boldIndices = boldIndices
                ).toMutableList()

                colorIndices = updateFormattedIndices(
                    currentText = currentText.text,
                    previousText = previousText,
                    boldIndices = colorIndices
                ).toMutableList()
                italicIndices = updateFormattedIndices(
                    currentText = currentText.text,
                    previousText = previousText,
                    boldIndices = italicIndices
                ).toMutableList()

                previousText = currentText.text

            },
            visualTransformation = formatter
        )
        Spacer(modifier = Modifier.height(100.dp))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                boldIndices = getFormattedIndices(
                    selectedTextRange = textFieldText.selection,
                    formattedIndices = boldIndices
                ).toMutableList()


            }) {
                Text(text = "Bold")
            }

            Button(onClick = {
                colorIndices = getFormattedIndices(
                    selectedTextRange = textFieldText.selection,
                    formattedIndices = colorIndices
                ).toMutableList()
            }) {
                Text(text = "Red Color")
            }
            Button(onClick = {
                italicIndices = getFormattedIndices(
                    selectedTextRange = textFieldText.selection,
                    formattedIndices = italicIndices
                ).toMutableList()
            }) {
                Text(text = "Italic")
            }
        }

    }

}

fun getFormattedIndices(selectedTextRange: TextRange, formattedIndices: List<Int>): List<Int> {
    val start = selectedTextRange.start
    val end = selectedTextRange.end
    val isSelectedSomeText = start != end
    val updatedIndices = formattedIndices.toMutableList()
    if (isSelectedSomeText)
        for (i in start until end) {
            updatedIndices.add(i)
        }
    //remove the duplicate element if present
    return updatedIndices.distinct()

}

fun updateFormattedIndices(
    currentText: String,
    previousText: String,
    boldIndices: List<Int>,
): List<Int> {
    val textChangeWatcher = TextChangeWatcher(
        currentText = currentText,
        previousText = previousText
    )

    if (boldIndices.isNotEmpty()) {
        if (textChangeWatcher.isCharacterInserted()) {
            /*
            this method has bug,
            if you insert the character before the index 0th character
            then this block will not execute
*/
            val index = textChangeWatcher.findInsertedCharacterIndex()
            return textChangeWatcher
                .rightShiftBoldedIndex(index, boldIndices)
        }
        if (textChangeWatcher.isSingleCharacterRemoved()) {
            return updateIndicesOnCharacterRemoval(
                previousText = previousText,
                currentText = currentText,
                boldedIndexes = boldIndices
            )
        }
    }
    return boldIndices

}