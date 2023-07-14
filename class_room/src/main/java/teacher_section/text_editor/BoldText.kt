package teacher_section.text_editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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

@Preview
@Composable
fun Pw() {
    // var boldIndices by remember { mutableStateOf(mutableListOf(3, 4, 7, 8)) }
    var boldIndices by remember { mutableStateOf(mutableListOf<Int>()) }
    var textFieldText by remember { mutableStateOf(TextFieldValue("0123456789")) }
    var previousText by remember { mutableStateOf("") }

    val formatter = TextEditorVisualTransformer().createTextFormatter(
        formattedIndices = boldIndices
    )


    Column(modifier = Modifier.padding(8.dp)) {
        TextField(
            value = textFieldText,
            onValueChange = { currentText ->
                textFieldText = currentText
                boldIndices = updateBoldedIndices(
                    currentText = currentText.text,
                    previousText = previousText,
                    boldIndices = boldIndices
                ).toMutableList()

                previousText = currentText.text

            },
            visualTransformation = formatter
        )
        Spacer(modifier = Modifier.height(100.dp))
        Button(onClick = {
            boldIndices = getBoldedIndices(
                selectedTextRange = textFieldText.selection,
                boldIndices = boldIndices
            ).toMutableList()

        }) {
            Text(text = "Bold")
        }
    }

}

fun getBoldedIndices(selectedTextRange: TextRange, boldIndices: List<Int>): List<Int> {
    val start = selectedTextRange.start
    val end = selectedTextRange.end
    val isSelectedSomeText = start != end
    val updatedIndices = boldIndices.toMutableList()
    if (isSelectedSomeText)
        for (i in start until end) {
            updatedIndices.add(i)
        }
    //remove the duplicate element if present
    return updatedIndices.distinct()

}

fun updateBoldedIndices(
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