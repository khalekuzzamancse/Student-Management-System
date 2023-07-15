package teacher_section.text_editor.text_formatting

import androidx.compose.ui.text.TextRange

object FormattedIndicesUpdater {
    fun updateFormattedIndices(
        currentText: String,
        previousText: String,
        formattedIndices: List<Int>,
    ): List<Int> {
        val textChangeWatcher = TextChangeWatcher(
            currentText = currentText,
            previousText = previousText
        )

        if (formattedIndices.isNotEmpty()) {
            if (textChangeWatcher.isCharacterInserted()) {
                /*
                this method has bug,
                if you insert the character before the index 0th character
                then this block will not execute
    */
                val index = textChangeWatcher.findInsertedCharacterIndex()
                return textChangeWatcher
                    .rightShiftBoldedIndex(index, formattedIndices)
            }
            if (textChangeWatcher.isSingleCharacterRemoved()) {
                return updateIndicesOnCharacterRemoval(
                    previousText = previousText,
                    currentText = currentText,
                    boldedIndexes = formattedIndices
                )
            }
        }
        return formattedIndices

    }
    fun updateIndicesOnCharacterRemoval(
        currentText: String,
        previousText: String,
        boldedIndexes: List<Int>,
    ): List<Int> {
        if (
            boldedIndexes.isEmpty()
            || currentText.isEmpty()
            || previousText.isEmpty()
            || currentText == previousText
        )
            return boldedIndexes
        return (FormattedIndexRemover(
            previousText = previousText,
            currentText = currentText,
            formattedTextIndices = boldedIndexes
        )
            .findRemovedCharacterIndex()
            .checkRemovedCharacterWasBolded()
            .removeIndexFromBoldedListIfPresent()
            .shiftIndicesToLeftBy1()
            .formattedTextIndices
                )
    }
     fun updateFormattedIndicesWithSelection(
        selectedTextRange: TextRange,
        formattedIndices: List<Int>,
    ): List<Int> {
        val start = selectedTextRange.start
        val end = selectedTextRange.end
        val isSelectedSomeText = start != end
        val updatedIndices = formattedIndices.toMutableList()
        if (isSelectedSomeText)
            for (i in start until end) {
                updatedIndices.add(i)
            }
        //remove the duplicate element if present
        return updatedIndices.distinct()

    }



}