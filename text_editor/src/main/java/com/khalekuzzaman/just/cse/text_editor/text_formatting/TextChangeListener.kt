package com.khalekuzzaman.just.cse.text_editor.text_formatting

import java.util.TreeMap

interface TextChangeListener {
    fun onTextChange(): TreeMap<Int, Set<Formatter>>
}

data class SingleCharacterChangeListener(
    private val changeUtils: SingleCharacterChangeUtils,
    private var formattedIndices: TreeMap<Int, Set<Formatter>>,
) : TextChangeListener {

    override fun onTextChange() = when {
        changeUtils.hasRemoval() -> {
            FormattedIndicesManager(formattedIndices)
                .onCharacterRemoval(changeUtils.getChangeIndex())
        }
        changeUtils.hasInsertion() -> {
            FormattedIndicesManager(formattedIndices)
                .onCharacterInsertion(changeUtils.getChangeIndex())
        }
        else -> formattedIndices
    }
}

