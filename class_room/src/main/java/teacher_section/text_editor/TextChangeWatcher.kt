package teacher_section.text_editor

data class TextChangeWatcher(
    val previousText: String,
    val currentText: String,
) {
    fun isCharAddedBefore(i: Int): Boolean {
        if (currentText.length <= previousText.length || i < 0 || i >= previousText.length) {
            return false
        }
        return previousText.substring(0, i) != currentText.substring(0, i)
    }

    fun isCharacterRemovedBeforeIndex(index: Int): Boolean {
        if (currentText.length < previousText.length) {
            val previousChar = if (index > 0) previousText[index - 1] else null
            val newChar = if (index > 0) currentText[index - 1] else null
            if (previousChar != newChar) {
                return true
            }
        }
        return false
    }

    fun is1CharacterRemoved() = previousText.length - currentText.length == 1

}