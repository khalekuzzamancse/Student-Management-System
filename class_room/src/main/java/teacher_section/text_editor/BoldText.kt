package teacher_section.text_editor

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
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
fun P() {

    var textFieldText by remember { mutableStateOf("") }
    val boldTextFormat = Regex("bold", RegexOption.IGNORE_CASE)

    val formatter = VisualTransformation { textSnapshot ->
        val transformedText = buildAnnotatedString {
            append(textSnapshot.text)
            boldTextFormat.findAll(textSnapshot.text).forEach { matchResult ->
                addStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold),
                    start = matchResult.range.start,
                    end = matchResult.range.endInclusive + 1
                )
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




    Column {
        TextField(
            value = textFieldText,
            onValueChange = { textFieldText = it },
            visualTransformation = formatter
        )
        Button(onClick = {
            textFieldText = textFieldText.replace("bold", "**bold**")
        }) {
            Text(text = "Format")
        }

    }





}

fun createBoldAnnotatedString(text: String, bolded: String): AnnotatedString {
    return buildAnnotatedString {
        append(text)
        // Apply bold style to all occurrences of "bold"
        var startIndex = text.indexOf(bolded)
        while (startIndex >= 0) {
            val endIndex = startIndex + bolded.length
            addStyle(
                style = SpanStyle(fontWeight = FontWeight.Bold),
                start = startIndex,
                end = endIndex
            )
            startIndex = text.indexOf(bolded, startIndex + 1)
        }
    }
}
