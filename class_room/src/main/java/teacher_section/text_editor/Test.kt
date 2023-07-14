package teacher_section.text_editor

fun main() {
    val a = "012345678"
    val b = "01235678"
    val list = listOf(3, 4)
    println(
        BoldedIndexRemover(
            previousText = a,
            currentText = b,
            boldedIndexes = list
        )
            .findRemovedCharacterIndex()
            .checkRemovedCharacterWasBolded()
            .removeIndexFromBoldedListIfPresent()
    )

}

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
            // println("Removed From:${INVALID_INDEX}")
            return this
        }
        for (i in previousText.indices) {
            val isBothStringIthCharacterIsDifferent = previousText[i] != currentText.getOrNull(i)
            if (isBothStringIthCharacterIsDifferent) {
                //   println("Removed From:${i}")
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
        if (!wasRemovedCharacterBolded)
            return this
        val updatedBoldedIndexes = boldedIndexes.filterNot { it == removedCharacterIndex }
        //println("Updated Indices:${updatedBoldedIndexes}")
        return this.copy(boldedIndexes = updatedBoldedIndexes)
    }
}


