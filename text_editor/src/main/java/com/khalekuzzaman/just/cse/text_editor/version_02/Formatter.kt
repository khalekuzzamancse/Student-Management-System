package com.khalekuzzaman.just.cse.text_editor.version_02

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration


interface Formatter{
    fun getStyle(): SpanStyle
}
object Formatters{
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

}