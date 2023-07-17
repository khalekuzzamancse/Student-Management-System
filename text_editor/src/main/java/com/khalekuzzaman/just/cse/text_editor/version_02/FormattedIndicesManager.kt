package com.khalekuzzaman.just.cse.text_editor.version_02

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import com.khalekuzzaman.just.cse.text_editor.version_02.ui.TreeMapUtilsImp
import java.util.TreeMap

/*
FormattedIndicesManager Class Responsibility:
It take a map that could be empty
then you can give a text range and ask him to format the range with a formatter.
after applying formatter you can take the updated formatter from it.

It Can do:
    Add/Remove formatter into a index/range of index.
    It can shift all the formatted index by some amount(left or right);
        while shifting it does not shift all the index,
        it shift only those index that need to shift.

 For the  purpose of chaining  and immutability  each function return the class instance

 */
data class FormattedIndicesManager(
    val formattedIndices: TreeMap<Int, Set<Formatter>>,
) {


    fun addFormatter(selectedTextRange: TextRange, formatter: Formatter): FormattedIndicesManager {
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

    fun addBoldFormatter(selectedTextRange: TextRange): FormattedIndicesManager {
        return addFormatter(selectedTextRange, Formatters.Bold)
    }

    fun addItalicFormatter(selectedTextRange: TextRange): FormattedIndicesManager {
        return addFormatter(selectedTextRange, Formatters.Italic)
    }

    fun addLineThroughFormatter(selectedTextRange: TextRange): FormattedIndicesManager {
        return addFormatter(selectedTextRange, Formatters.LineThrough)
    }

    fun addUnderLineFormatter(selectedTextRange: TextRange): FormattedIndicesManager {
        return addFormatter(selectedTextRange, Formatters.UnderLine)
    }

    fun addColorFormatter(selectedTextRange: TextRange, color: Color): FormattedIndicesManager {
        return addFormatter(selectedTextRange, Formatters.Colored(color))
    }

    fun addHighlightFormatter(selectedTextRange: TextRange, color: Color): FormattedIndicesManager {
        return addFormatter(selectedTextRange, Formatters.HighLight(color))
    }

    fun addFontSizeFormatter(selectedTextRange: TextRange, fontSize: Int): FormattedIndicesManager {
        return addFormatter(selectedTextRange, Formatters.FontSize(fontSize))
    }

    fun shiftFormattedIndices(
        characterChangedAt: Int,
        shiftAmount: Int,
    ) = TreeMapUtilsImp(formattedIndices).shiftKey(shiftAmount) { characterChangedAt <= it }

    fun removeIndex(index: Int) = TreeMapUtilsImp(formattedIndices).remove(key = index)
    fun isFormatted(key: Int) = TreeMapUtilsImp(formattedIndices).doesExits(key)
    fun isIndexWithinFormattedRange(key: Int) =
        TreeMapUtilsImp(formattedIndices).areNeighborsEqual(key)

    fun useSameFormatAsNeighbors(index: Int): TreeMap<Int, Set<Formatter>> {
        val newMap = TreeMap(formattedIndices)
        newMap[index] = TreeMapUtilsImp(newMap).getNeighbourCommons(key = index)
        return newMap
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