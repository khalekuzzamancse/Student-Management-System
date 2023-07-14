package teacher_section.text_editor

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight

data class TextEditorTextFormatter(
    private val formattedIndices: List<Int>,
    val text: AnnotatedString,
    val characterFormatter: CharacterFormatter,
) {
    fun formatWholeText(): TextEditorTextFormatter {
        val annotatedString = buildAnnotatedString {
            text.forEachIndexed { index, char ->
                if (shouldThisIndexBeFormatted(index)) {
                   // append(formatCharacter(char))
                    append(characterFormatter.formatCharacter(char))
                } else {
                    //need not to format character
                    append(char)
                }
            }
        }
        return this.copy(text = annotatedString)
    }

    private fun shouldThisIndexBeFormatted(index: Int): Boolean {
        val formattedIndex = formattedIndices.indexOf(index)
        return formattedIndex != -1
    }

    private fun formatCharacter(char: Char): AnnotatedString {
        return AnnotatedString(
            text = char.toString(),
            spanStyle = SpanStyle(fontWeight = FontWeight.Bold)
        )
    }
}