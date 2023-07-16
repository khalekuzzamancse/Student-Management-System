package com.khalekuzzaman.just.cse.text_editor.version_02

import androidx.compose.ui.text.TextRange
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