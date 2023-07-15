package teacher_section.text_editor.text_formatting


fun updateIndicesOnCharacterRemoval(
    currentText: String,
    previousText: String,
    boldedIndexes: List<Int>,
): List<Int> {
    if (
        boldedIndexes.isEmpty()
        || currentText.isEmpty()
        || previousText.isEmpty()
        || currentText == previousText
    )
        return boldedIndexes
    return (FormattedIndexRemover(
        previousText = previousText,
        currentText = currentText,
        formattedTextIndices = boldedIndexes
    )
        .findRemovedCharacterIndex()
        .checkRemovedCharacterWasBolded()
        .removeIndexFromBoldedListIfPresent()
        .shiftIndicesToLeftBy1()
        .formattedTextIndices
            )
}







