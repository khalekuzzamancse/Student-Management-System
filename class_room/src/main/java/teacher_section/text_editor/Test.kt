package teacher_section.text_editor

import android.util.Log

fun main() {
    val a = "012345678"
    val b = "01235678"
    val list = listOf(3, 4)
    println(
        modifyBoldedIndices(list,3)
    )

}
fun modifyBoldedIndices(boldedIndices: List<Int>, index: Int): List<Int> {
    return boldedIndices.map { if (it >= index) it - 1 else it }
}

fun updateBoldTexts(
    previousText: String,
    currentText: String,
    boldIndices: List<Int>,
): List<Int> {
    if (boldIndices.isEmpty())
        return boldIndices

    val charAddedBeforeIndex =
        isCharAddedBefore(previousText, currentText, boldIndices.first())
    val charRemovedBeforeIndex =
        isCharacterRemovedBeforeIndex(previousText, currentText, boldIndices.first())
    val isTextRemoved = previousText.length - currentText.length == 1
    //
    Log.i("TEXTTTTT", "$previousText\n${currentText}")
    var updateBoldIndices = mutableListOf<Int>()


    if (isTextRemoved) {
        updateBoldIndices = updateBoldedListIfBoldedCharacterRemoved(
            previousText = previousText,
            currentText = currentText,
            boldedIndexes = boldIndices
        ).toMutableList()
    }

    when {
        charAddedBeforeIndex -> {
            updateBoldIndices = boldIndices.map { it + 1 }.toMutableList()
        }

        charRemovedBeforeIndex -> {
            //check if the removed character was bolded
            updateBoldIndices = boldIndices.map { it - 1 }.filter { it >= 0 }.toMutableList()
        }
    }
    return updateBoldIndices
}



