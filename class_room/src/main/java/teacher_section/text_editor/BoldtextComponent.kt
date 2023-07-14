package teacher_section.text_editor

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle


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
    return (BoldedIndexRemover(
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







