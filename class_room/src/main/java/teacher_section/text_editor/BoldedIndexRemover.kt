package teacher_section.text_editor

import android.util.Log

data class BoldedIndexRemover(
    private val currentText: String,
    private val previousText: String,
    val boldedIndexes: List<Int>,
    private val removedCharacterIndex: Int = INVALID_INDEX,
    private val wasRemovedCharacterBolded: Boolean = false,
) {
    companion object {
        const val INVALID_INDEX = -1
    }


    //For using the chaining we return the new instance of the class
    //by updating data
    fun findRemovedCharacterIndex(): BoldedIndexRemover {
        val isMultipleCharacterRemoved = (previousText.length - currentText.length != 1)
        if (isMultipleCharacterRemoved) {
            //    println("Removed From:${INVALID_INDEX}")
            return this
        }
        for (i in previousText.indices) {
            val isBothStringIthCharacterIsDifferent = previousText[i] != currentText.getOrNull(i)
            if (isBothStringIthCharacterIsDifferent) {
                //  println("Removed From:${i}")
                return this.copy(removedCharacterIndex = i)
            }
        }
        // println("Removed From:${INVALID_INDEX}")
        // No difference found
        return this

    }

    fun checkRemovedCharacterWasBolded(): BoldedIndexRemover {
        //
        val isRemovedIndexPresentInBoldedIndexSet =
            boldedIndexes.contains(removedCharacterIndex)
        //  println("Removed Char was Bolded:${isRemovedIndexPresentInBoldedIndexSet}")
        return this.copy(wasRemovedCharacterBolded = isRemovedIndexPresentInBoldedIndexSet)
    }

    fun removeIndexFromBoldedListIfPresent(): BoldedIndexRemover {
        if (!wasRemovedCharacterBolded) {
            Log.i(
                "BOLDED_REMOVER:\n",
                "RemovedIndex:$removedCharacterIndex (not bolded)\n" +
                        "PreviousText:${previousText}\n" +
                        "CurrentText:${currentText}\n" +
                        "RecentBoldedIndices:$boldedIndexes"
            )
            return this
        }

        val updatedBoldedIndexes = boldedIndexes
            .filterNot { it == removedCharacterIndex }
        Log.i(
            "BOLDED_REMOVER:\n",
            "RemovedIndex:$removedCharacterIndex (bolded)\n" +
                    "PreviousText:${previousText}\n" +
                    "CurrentText:${currentText}\n" +
                    "RecentBoldedIndices:$updatedBoldedIndexes"
        )
        return this.copy(boldedIndexes = updatedBoldedIndexes)
    }

    fun shiftIndicesToLeftBy1(): BoldedIndexRemover {
        //only shift the bolded index if it any of the previous character removed
        //
        val isCharacterRemoved = previousText.length == currentText.length + 1
        if (!isCharacterRemoved){
            Log.i(
                "BOLDED_REMOVER:\n",
                "shift():::\n" +
                        "RemovedIndex:$removedCharacterIndex\n" +
                        "RecentBoldedIndices(not shifted):$boldedIndexes"
            )
            return this
        }

        val updatedBoldedIndexes =boldedIndexes.map { if (it >= removedCharacterIndex) it - 1 else it }
        Log.i(
            "BOLDED_REMOVER:\n",
            "shift():::\n" +
                    "RemovedIndex:$removedCharacterIndex\n" +
                    "RecentBoldedIndices(shifted):$updatedBoldedIndexes"
        )
        return this.copy(boldedIndexes = updatedBoldedIndexes)
    }


}