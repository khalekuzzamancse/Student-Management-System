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

    fun is1CharacterRemoved() = previousText.length - currentText.length == 1

}