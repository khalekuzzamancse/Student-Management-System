package teacher_section.text_editor.text_formatting

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

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

    object UnderLineFormatter : CharacterFormatter {
        override fun getStyle() = SpanStyle(
            textDecoration = TextDecoration.Underline
        )
    }

    object LineThroughFormatter : CharacterFormatter {
        override fun getStyle() = SpanStyle(
            textDecoration = TextDecoration.LineThrough
        )
    }
}

data class ColorFormatter(
    val color: Color,
) : CharacterFormatter {
    override fun getStyle() = SpanStyle(color)

}
