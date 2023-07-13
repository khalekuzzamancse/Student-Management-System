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
    var boldIndices by remember { mutableStateOf(mutableListOf(2, 3)) }


    var textFieldText by remember { mutableStateOf(TextFieldValue()) }
    var previousText by remember { mutableStateOf("") }

    val formatter = VisualTransformation { textSnapshot ->
        val transformedText = buildAnnotatedString {
            with(textSnapshot) {
                text.forEachIndexed { index, char ->
                    val boldIndex = boldIndices.indexOf(index)
                    val fontWeight =
                        if (boldIndex != -1) FontWeight.Bold else FontWeight.Normal
                    withStyle(style = SpanStyle(fontWeight = fontWeight)) {
                        append(char)
                    }
                }
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return offset
            }

            override fun transformedToOriginal(offset: Int): Int {
                return offset
            }
        }

        TransformedText(transformedText, offsetMapping)
    }

    TextField(
        value = textFieldText,
        onValueChange = { newValue ->
            textFieldText = newValue
            if(isCharAddedBefore(previousText,textFieldText.text,boldIndices.first())){
                boldIndices= boldIndices.map { it + 1 }.toMutableList()
            }
            if(isCharacterRemovedBeforeIndex(previousText,textFieldText.text,boldIndices.first())){
                boldIndices= boldIndices.map { it -1 }.toMutableList()
            }
            previousText = newValue.text

        },
        visualTransformation = formatter
    )
}

fun isCharAddedBefore(original: String, modified: String, i: Int): Boolean {
    if (modified.length <= original.length || i < 0 || i >= original.length) {
        return false
    }
    return original.substring(0, i) != modified.substring(0, i)
}
fun isCharacterRemovedBeforeIndex(previousText: String, currentString: String, index: Int): Boolean {
    if (currentString.length < previousText.length) {
        val previousChar = if (index > 0) previousText[index - 1] else null
        val newChar = if (index > 0) currentString[index - 1] else null
        if (previousChar != newChar) {
            return true
        }
    }
    return false
}

