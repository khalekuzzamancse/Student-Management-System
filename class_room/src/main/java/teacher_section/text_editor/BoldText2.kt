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
    var textFieldText by remember { mutableStateOf(TextFieldValue()) }
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
                if (textChangeWatcher.isCharAddedBefore(boldIndices.first())) {
                    boldIndices = boldIndices.map { it + 1 }.toMutableList()
                }

                if (textChangeWatcher.is1CharacterRemoved()) {
                    boldIndices = updateBoldedListIfBoldedCharacterRemoved(
                        previousText = previousText,
                        currentText = textFieldText.text,
                        boldedIndexes = boldIndices
                    ).toMutableList()
                }
            }

            previousText = currentText.text

        },
        visualTransformation = formatter
    )
}

