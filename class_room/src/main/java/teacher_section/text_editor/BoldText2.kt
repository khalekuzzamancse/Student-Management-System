package teacher_section.text_editor

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
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
        onValueChange = { newValue ->
            textFieldText = newValue
            val charAddedBeforeIndex = isCharAddedBefore(previousText, textFieldText.text, boldIndices.first())
            val charRemovedBeforeIndex = isCharacterRemovedBeforeIndex(previousText, textFieldText.text, boldIndices.first())
            previousText = newValue.text

            when {
                charAddedBeforeIndex -> {
                    boldIndices = boldIndices.map { it + 1 }.toMutableList()
                }
                charRemovedBeforeIndex -> {
                    boldIndices = boldIndices.map { it - 1 }.filter { it >= 0 }.toMutableList()
                }
            }
        },
        visualTransformation = formatter
    )
}


