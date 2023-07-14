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

    fun rightShiftBoldedIndex(index: Int, formattedTextIndices: List<Int>): List<Int> {
        /*
        this method seems okay,but it has a bug
        when after launch the editor,if user add a character
        at the begging then it showing unwanted behaviour fix it.
        example as:
        let we have: 012345678 ,boldIndices[3,4]
        if we add the very 1st character before 0,then it showing not shifting the character
         */
        return formattedTextIndices.map { if (index <= it) it + 1 else it }
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