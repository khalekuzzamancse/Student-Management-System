package teacher_section.text_editor

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

interface CharacterFormatter{
    fun formatCharacter(char: Char): AnnotatedString
}

object CharacterFormatters{
        object CharacterBoldFormatter:CharacterFormatter {
            override fun formatCharacter(char: Char): AnnotatedString {
                return AnnotatedString(
                    text = char.toString(),
                    spanStyle = SpanStyle(fontWeight = FontWeight.Bold)
                )
            }
        }
        object CharacterItalicFormatter:CharacterFormatter {
            override fun formatCharacter(char: Char): AnnotatedString {
                return AnnotatedString(
                    text = char.toString(),
                    spanStyle = SpanStyle(fontStyle = FontStyle.Italic)
                )
            }

    }
}
