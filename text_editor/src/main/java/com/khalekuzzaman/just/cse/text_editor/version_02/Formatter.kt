package com.khalekuzzaman.just.cse.text_editor.version_02

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp


interface Formatter {
    fun getStyle(): SpanStyle
}

object Formatters {
    object Bold : Formatter {
        override fun getStyle() = SpanStyle(fontWeight = FontWeight.Bold)
    }

    object Italic : Formatter {
        override fun getStyle() = SpanStyle(fontStyle = FontStyle.Italic)
    }

    object RedColor : Formatter {
        override fun getStyle() = SpanStyle(color = Color.Red)
    }

    object UnderLine : Formatter {
        override fun getStyle() = SpanStyle(
            textDecoration = TextDecoration.Underline
        )
    }

    object LineThrough : Formatter {
        override fun getStyle() = SpanStyle(
            textDecoration = TextDecoration.LineThrough
        )
    }

    data class Colored(
        val color: Color,
    ) : Formatter {
        override fun getStyle() = SpanStyle(color = color)

    }

    data class FontSize(
        val fontSize: Int,
    ) : Formatter {
        override fun getStyle() =
            SpanStyle(
                fontSize = fontSize.sp
            )

    }

    data class HighLight(
        val color: Color,
    ) : Formatter {
        override fun getStyle() =
            SpanStyle(
                background = color
            )

    }

}