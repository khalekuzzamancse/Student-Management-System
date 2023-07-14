package teacher_section.text_editor

fun main() {
    println(findRemovedCharacterIndex("12345678","1234567"))
}

fun findRemovedCharacterIndex(previousText: String, currentText: String): Int {
    val isMoreMultipleCharacterRemoved = (previousText.length - currentText.length != 1)
    if (isMoreMultipleCharacterRemoved) {
        return -1
    }
    for (i in previousText.indices) {
        val isBothStringIthCharacterIsDifferent = previousText[i] != currentText.getOrNull(i)
        if (isBothStringIthCharacterIsDifferent) {
            return i
        }
    }
    // No difference found
    return -1
}
