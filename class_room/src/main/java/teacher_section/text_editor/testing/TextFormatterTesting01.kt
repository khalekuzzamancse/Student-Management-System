package teacher_section.text_editor.testing

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import teacher_section.text_editor.text_formatting.CharacterFormatter
import teacher_section.text_editor.text_formatting.Formatter
import teacher_section.text_editor.text_formatting.TextEditorTextFormatter

@Preview
@Composable
private fun Preview() {
    var text by remember {
        mutableStateOf(AnnotatedString(""))
    }

    var annotatedString = AnnotatedString(text = "012345678")
    val formatters = listOf(
        Formatter(
            indices = listOf(3, 4),
            formatter = CharacterFormatter.BoldFormatter
        ),
        Formatter(
            indices = listOf(4, 5),
            formatter = CharacterFormatter.ItalicFormatter
        ),
        Formatter(
            indices = listOf(7, 4, 5),
            formatter = CharacterFormatter.RedColorFormatter
        )
    )
    annotatedString = TextEditorTextFormatter(
        formatter = formatters,
        text = annotatedString
    )
        .format()
    text = annotatedString

    Text(text = text)
}










