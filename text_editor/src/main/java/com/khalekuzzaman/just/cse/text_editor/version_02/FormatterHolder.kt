package com.khalekuzzaman.just.cse.text_editor.version_02

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.unit.TextUnit
import java.util.TreeMap

data class FormatterHolder(
    val formattedIndices: TreeMap<Int, Set<Formatter>>,
) {


    fun addFormatter(selectedTextRange: TextRange, formatter: Formatter): FormatterHolder {
        val newFormattedIndicesMap = TreeMap(formattedIndices)
        val start = selectedTextRange.start
        val end = selectedTextRange.end
        val isSelectedSomeText = start != end
        if (isSelectedSomeText)
            for (i in start until end) {
                val existingFormatters = newFormattedIndicesMap[i] ?: emptySet()
                val newFormatters = existingFormatters + formatter
                newFormattedIndicesMap[i] = newFormatters
            }

        return this.copy(formattedIndices = newFormattedIndicesMap)
    }

    fun addBoldFormatter(selectedTextRange: TextRange): FormatterHolder {
        return addFormatter(selectedTextRange, Formatters.Bold)
    }

    fun addItalicFormatter(selectedTextRange: TextRange): FormatterHolder {
        return addFormatter(selectedTextRange, Formatters.Italic)
    }

    fun addLineThroughFormatter(selectedTextRange: TextRange): FormatterHolder {
        return addFormatter(selectedTextRange, Formatters.LineThrough)
    }

    fun addUnderLineFormatter(selectedTextRange: TextRange): FormatterHolder {
        return addFormatter(selectedTextRange, Formatters.UnderLine)
    }

    fun addColorFormatter(selectedTextRange: TextRange, color: Color): FormatterHolder {
        return addFormatter(selectedTextRange, Formatters.Colored(color))
    }

    fun addHighlightFormatter(selectedTextRange: TextRange, color: Color): FormatterHolder {
        return addFormatter(selectedTextRange, Formatters.HighLight(color))
    }

    fun addFontSizeFormatter(selectedTextRange: TextRange, fontSize: Int): FormatterHolder {
        return addFormatter(selectedTextRange, Formatters.FontSize(fontSize))
    }


    override fun toString(): String {
        for ((key, value) in formattedIndices) {
            val names = value.map { it.javaClass.simpleName }
            println("$key:${names.joinToString(",", "[", "]")}")
        }
        return super.toString()
    }
}

//fun main() {
//   val h=FormatterHolder(TreeMap())
//        .addFormatter(TextRange(3, 5), Formatters.RedColor)
//    val a = "012345678"
//    val b = "0123456718"
//
//    val listener = SingleCharacterChangeListener(
//        SingleCharacterChangeUtils(previousText = a, currentText = b),
//        TreeMap(h.formattedIndices)
//    )
//   val map= listener.onTextChange()
//    for ((key, value) in map) {
//        val names = value.map { it.javaClass.simpleName }
//        println("$key:${names.joinToString(",", "[", "]")}")
//    }
//
//}