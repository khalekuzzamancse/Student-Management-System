package teacher_section.text_editor

import android.util.Log
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle


fun updateBoldedListIfBoldedCharacterRemoved(
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
    val list = BoldedIndexRemover(
        previousText = previousText,
        currentText = currentText,
        boldedIndexes = boldedIndexes
    )
        .findRemovedCharacterIndex()
        .checkRemovedCharacterWasBolded()
        .removeIndexFromBoldedListIfPresent()
        .shiftIndicesToLeftBy1()
        .boldedIndexes
    return list
}


fun createTextFormatter(boldIndices: List<Int>): VisualTransformation {
    return VisualTransformation { textSnapshot ->
        val transformedText = buildAnnotatedString {
            with(textSnapshot) {
                text.forEachIndexed { index, char ->
                    val boldIndex = boldIndices.indexOf(index)
                    val fontWeight =
                        if (boldIndex != -1) FontWeight.Bold else FontWeight.Normal
                    withStyle(style = SpanStyle(fontWeight = fontWeight)) {
                        append(char)
                    }
                }
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return offset
            }

            override fun transformedToOriginal(offset: Int): Int {
                return offset
            }
        }

        TransformedText(transformedText, offsetMapping)
    }
}




