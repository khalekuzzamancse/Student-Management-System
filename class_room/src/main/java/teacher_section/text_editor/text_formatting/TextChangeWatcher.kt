package teacher_section.text_editor.text_formatting

data class TextChangeWatcher(
    val previousText: String,
    val currentText: String,
) {

    fun rightShiftBoldedIndex(index: Int, formattedTextIndices: List<Int>): List<Int> {
        return formattedTextIndices.map { if (index <= it) it + 1 else it }
    }

    fun isCharacterInserted(): Boolean {
        return currentText.length == previousText.length + 1
    }


    fun isSingleCharacterRemoved() = previousText.length - currentText.length == 1
    fun findInsertedCharacterIndex(): Int {
        val isSingleCharacterInserted = currentText.length - previousText.length == 1
        if (!isSingleCharacterInserted) {
            return -1
        }

        for (i in previousText.indices) {
            if (previousText[i] != currentText[i]) {
                return i
            }
        }
        // The inserted character is at the end of the current text
        return previousText.length
    }


}