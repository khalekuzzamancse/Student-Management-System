package teacher_section.text_editor

import android.util.Log
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
    var boldIndices by remember { mutableStateOf(mutableListOf(3, 4)) }
    var textFieldText by remember { mutableStateOf(TextFieldValue("012345678")) }
    var previousText by remember { mutableStateOf("") }
    val formatter = createTextFormatter(boldIndices)
    TextField(
        value = textFieldText,
        onValueChange = { currentText ->
            textFieldText = currentText

            val textChangeWatcher = TextChangeWatcher(
                currentText = currentText.text,
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
                    Log.i(
                        "OKAYYYYY", "character add at:$index\n" +
                                "BoldedIndexOld:$boldIndices"
                    )
                    boldIndices = textChangeWatcher
                        .rightShiftBoldedIndex(index, boldIndices)
                        .toMutableList()
                    Log.i("OKAYYYYY", "BoldedIndexNew:$boldIndices")
                }

                if (textChangeWatcher.is1CharacterRemoved()) {
                    Log.i("OKAYYYYY", "removed:$boldIndices")
                    boldIndices = updateBoldedListIfBoldedCharacterRemoved(
                        previousText = previousText,
                        currentText = textFieldText.text,
                        boldedIndexes = boldIndices
                    ).toMutableList()
                    Log.i("OKAYYYYY", "BoldedIndexNew:$boldIndices")
                }
            }

            previousText = currentText.text

        },
        visualTransformation = formatter
    )
}

