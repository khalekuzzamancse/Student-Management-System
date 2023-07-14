package teacher_section.text_editor

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun Pw() {
    var boldIndices by remember { mutableStateOf(mutableListOf(3, 4,7,8)) }
    var textFieldText by remember { mutableStateOf(TextFieldValue("0123456789")) }
    var previousText by remember { mutableStateOf("") }
    val formatter = createTextFormatter(boldIndices)
    TextField(
        value = textFieldText,
        onValueChange = { currentText ->
            textFieldText = currentText

           boldIndices= updateBoldedIndices(
                currentText=currentText.text,
                previousText=previousText,
                boldIndices=boldIndices).toMutableList()

            previousText = currentText.text

        },
        visualTransformation = formatter
    )
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
        if (textChangeWatcher.is1CharacterRemoved()) {
            return updateBoldedListIfBoldedCharacterRemoved(
                previousText = previousText,
                currentText = currentText,
                boldedIndexes = boldIndices
            )
        }
    }
    return boldIndices

}