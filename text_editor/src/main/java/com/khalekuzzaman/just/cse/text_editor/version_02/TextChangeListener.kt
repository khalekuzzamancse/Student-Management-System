package com.khalekuzzaman.just.cse.text_editor.version_02

import java.util.TreeMap

interface TextChangeListener {
    fun onTextChange(): TreeMap<Int, Set<Formatter>>
}


data class SingleCharacterChangeListener(
    private val changeUtils: SingleCharacterChangeUtils,
    private var formattedIndices: TreeMap<Int, Set<Formatter>>,
) : TextChangeListener {

    override fun onTextChange(): TreeMap<Int, Set<Formatter>> {
        if (changeUtils.isTextUnchanged())
            return formattedIndices

        if (changeUtils.hasRemoval()) {
            val removedFrom = changeUtils.getChangeIndex()
            val manager = FormattedIndicesManager(formattedIndices)
            if (manager.isFormatted(removedFrom))
                formattedIndices = manager.removeIndex(removedFrom)
        }
//        if (changeUtils.hasInsertion()) {
//            val manager = FormattedIndicesManager(formattedIndices)
//            val insertedAt = changeUtils.getChangeIndex()
//            if (manager.isIndexWithinFormattedRange(insertedAt))
//                formattedIndices = manager.useSameFormatAsNeighbors(insertedAt)
//        }

        return FormattedIndicesManager(formattedIndices)
            .shiftFormattedIndices(
                characterChangedAt = changeUtils.getChangeIndex(),
                shiftAmount = changeUtils.getShiftAmount()
            )

    }


}
