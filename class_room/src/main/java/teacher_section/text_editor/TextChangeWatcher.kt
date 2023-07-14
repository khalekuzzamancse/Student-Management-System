package teacher_section.text_editor

data class TextChangeWatcher(
    val previousText: String,
    val currentText: String,
) {
    fun isCharAddedBefore(i: Int): Boolean {
        //this method may have unknown bugs,later fix this if needed
        if (currentText.length <= previousText.length || i < 0 || i >= previousText.length) {
            return false
        }
        return previousText.substring(0, i) != currentText.substring(0, i)
    }
    fun rightShiftBoldedIndex(index: Int, boldedIndices: List<Int>): List<Int> {
        return boldedIndices.map {  if(index<=it)it + 1 else it }
    }
    fun isCharacterInserted(): Boolean {
        return currentText.length == previousText.length + 1
    }


    fun is1CharacterRemoved() = previousText.length - currentText.length == 1
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