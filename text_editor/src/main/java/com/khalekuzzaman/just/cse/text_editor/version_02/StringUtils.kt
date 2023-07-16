package com.khalekuzzaman.just.cse.text_editor.version_02

interface StringUtils {
    fun hasInsertion(): Boolean
    fun hasRemoval(): Boolean
    fun isTextChanged(): Boolean
    fun isTextUnchanged(): Boolean
    fun getShiftAmount(): Int
    fun getChangeIndex(): Int
}

data class SingleCharacterChangeUtils(
    private val previousText: String,
    private val currentText: String,
) : StringUtils {

    companion object {
        const val NO_CHANGE_INDEX = -1
        const val NO_SHIFT_AMOUNT = 0
        const val LEFT_SHIFT_AMOUNT = -1
        const val RIGHT_SHIFT_AMOUNT = 1
    }

    private fun getRemovalIndex(): Int {
        require(previousText.length == currentText.length + 1) {
            "The previous text must be longer than the current text by one character"
        }
        for (i in currentText.indices) {
            if (currentText[i] != previousText[i]) {
                return i
            }
        }
        /*
        otherwise character removed from end
         */
        return currentText.length
    }


    private fun getInsertionIndex(): Int {
        require(currentText.length == previousText.length + 1) {
            "The current text must be longer than the previous text by one character"
        }
        for (i in previousText.indices) {
            if (previousText[i] != currentText[i]) {
                return i
            }
        }
        /*
        otherwise the character inserted in the at the end
                 */
        return previousText.length
    }

    override fun hasInsertion() = currentText.length == previousText.length + 1

    override fun hasRemoval() = previousText.length == currentText.length + 1
    override fun isTextChanged() = hasRemoval() || hasInsertion()
    override fun isTextUnchanged() = !isTextChanged()

    override fun getShiftAmount() = when {
        hasInsertion() && getInsertionIndex() == previousText.length -> NO_SHIFT_AMOUNT
        hasRemoval() && getRemovalIndex() == currentText.length -> NO_SHIFT_AMOUNT
        hasInsertion() -> RIGHT_SHIFT_AMOUNT
        hasRemoval() -> LEFT_SHIFT_AMOUNT
        else -> NO_SHIFT_AMOUNT
    }


    override fun getChangeIndex() = when {
        hasInsertion() -> getInsertionIndex()
        hasRemoval() -> getRemovalIndex()
        else -> NO_CHANGE_INDEX
    }

}

fun main() {
    val a = "0123456789"
    val b = "01234567890"
    val u = SingleCharacterChangeUtils(previousText = a, currentText = b)
    println(u.isTextChanged())
    println(u.isTextUnchanged())

//    println(u.hasRemoval())
//    println(u.getChangeIndex())
//    println(u.getShiftAmount())
}