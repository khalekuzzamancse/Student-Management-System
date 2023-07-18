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

    companion object {
        private const val SINGLE_CHAR_ADD_SHIFT_AMOUNT = 1
        private const val SINGLE_CHAR_REMOVE_SHIFT_AMOUNT = -1
    }

    fun onCharacterRemoval(index: Int): TreeMap<Int, Set<Formatter>> {
        return (if (isFormatted(index))
            this
                .removeIndex(index)
                .shiftFormattedIndices(index, SINGLE_CHAR_REMOVE_SHIFT_AMOUNT)
                .formattedIndices
        else
            this
                .shiftFormattedIndices(index, SINGLE_CHAR_REMOVE_SHIFT_AMOUNT)
                .formattedIndices
                )
    }

    fun onCharacterInsertion(index: Int): TreeMap<Int, Set<Formatter>> {
        return if (isIndexWithinFormattedRange(index))
            useSameFormatAsNeighbors(index).formattedIndices
        else this
            .shiftFormattedIndices(index, SINGLE_CHAR_ADD_SHIFT_AMOUNT)
            .formattedIndices

    }


    fun addFormatter(selectedTextRange: TextRange, formatter: Formatter): FormattedIndicesManager {
        var newMap = TreeMap(formattedIndices)
        val start = selectedTextRange.start
        val end = selectedTextRange.end
        val isSelectedSomeText = start != end
        if (isSelectedSomeText) {
            newMap = TreeMapUtilsImp(newMap).add(start.until(end).toList(), formatter)
        }
        return this.copy(formattedIndices = newMap)
    }

    private fun addFormatter(index: Int, formatters: Set<Formatter>): FormattedIndicesManager {
        formattedIndices[index] = formatters
        return this.copy(formattedIndices = formattedIndices)
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

    private fun shiftFormattedIndices(
        characterChangedAt: Int,
        shiftAmount: Int,
    ): FormattedIndicesManager {
        return this.copy(
            formattedIndices = TreeMapUtilsImp(formattedIndices).shiftKey(shiftAmount) { characterChangedAt <= it }
        )
    }

    private fun removeIndex(index: Int): FormattedIndicesManager {
        return this.copy(formattedIndices = TreeMapUtilsImp(formattedIndices).remove(key = index))
    }

    private fun isFormatted(key: Int) = TreeMapUtilsImp(formattedIndices).doesExits(key)
    private fun isIndexWithinFormattedRange(key: Int) =
        TreeMapUtilsImp(formattedIndices).hasNeighbourCommon(key)

    private fun useSameFormatAsNeighbors(index: Int): FormattedIndicesManager {
        val commonFormat = TreeMapUtilsImp(formattedIndices).getPreviousOf(key = index)
        return this.copy(
            formattedIndices = this
                .shiftFormattedIndices(index, 1)
                .addFormatter(index, commonFormat)
                .formattedIndices
        )
    }

}
