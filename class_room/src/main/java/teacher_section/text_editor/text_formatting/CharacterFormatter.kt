package teacher_section.text_editor.text_formatting

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

interface CharacterFormatter {
    fun getStyle(): SpanStyle

    object BoldFormatter : CharacterFormatter {
        override fun getStyle() = SpanStyle(fontWeight = FontWeight.Bold)
    }

    object ItalicFormatter : CharacterFormatter {
        override fun getStyle() = SpanStyle(fontStyle = FontStyle.Italic)
    }

    object RedColorFormatter : CharacterFormatter {
        override fun getStyle() = SpanStyle(color = Color.Red)
    }
}
