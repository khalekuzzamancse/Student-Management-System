package com.khalekuzzaman.just.cse.text_editor.text_formatting

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
    val indices: TreeMap<Int, Set<Formatter>>,
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
                .indices
        else
            this
                .shiftFormattedIndices(index, SINGLE_CHAR_REMOVE_SHIFT_AMOUNT)
                .indices
                )
    }

    fun onCharacterInsertion(index: Int): TreeMap<Int, Set<Formatter>> {
        return if (isIndexWithinFormattedRange(index))
            useSameFormatAsNeighbors(index).indices
        else this
            .shiftFormattedIndices(index, SINGLE_CHAR_ADD_SHIFT_AMOUNT)
            .indices

    }


    fun addFormatter(indices: List<Int>, formatter: Formatter): FormattedIndicesManager {
        var newMap = TreeMap(this.indices)
        newMap = TreeMapUtilsImp(newMap).add(indices, formatter)
        return this.copy(indices = newMap)
    }

    private fun addFormatter(index: Int, formatters: Set<Formatter>): FormattedIndicesManager {
        indices[index] = formatters
        return this.copy(indices = indices)
    }


     fun shiftFormattedIndices(
        characterChangedAt: Int,
        shiftAmount: Int,
    ): FormattedIndicesManager {
        return this.copy(
            indices = TreeMapUtilsImp(indices).shiftKey(shiftAmount) { characterChangedAt <= it }
        )
    }

    private fun removeIndex(index: Int): FormattedIndicesManager {
        return this.copy(indices = TreeMapUtilsImp(indices).remove(key = index))
    }

    private fun isFormatted(key: Int) = TreeMapUtilsImp(indices).doesExits(key)
    fun doesExits(key: Int, formatter: Formatter) =
        TreeMapUtilsImp(indices).doesExits(key, formatter)

    fun doesExits(keys: List<Int>, formatter: Formatter): Boolean {
        var exits = true
        keys.forEach { key ->
            exits = exits && doesExits(key, formatter)
        }
        return exits
    }


    private fun isIndexWithinFormattedRange(key: Int) =
        TreeMapUtilsImp(indices).hasNeighbourCommon(key)

    private fun useSameFormatAsNeighbors(index: Int): FormattedIndicesManager {
        val commonFormat = TreeMapUtilsImp(indices).getPreviousOf(key = index)
        return this.copy(
            indices = this
                .shiftFormattedIndices(index, 1)
                .addFormatter(index, commonFormat)
                .indices
        )
    }

    fun removeFormat(index: Int): FormattedIndicesManager {
        return this.copy(indices = TreeMapUtilsImp(indices).remove(index))
    }

    fun removeFormat(index: Int, formatter: Formatter): FormattedIndicesManager {
        return this.copy(indices = TreeMapUtilsImp(indices).remove(index, formatter))
    }

    fun removeFormat(keys: List<Int>, formatter: Formatter): FormattedIndicesManager {
        return this.copy(indices = TreeMapUtilsImp(indices).remove(keys, formatter))
    }
    fun removeFormat(keys: List<Int>): FormattedIndicesManager {
        return this.copy(indices = TreeMapUtilsImp(indices).remove(keys))
    }

}
