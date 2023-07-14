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
        .boldedIndexes
    Log.i("BOLDED_REMOVER:REC", "$list")
    return list
}


data class BoldedIndexRemover(
    private val currentText: String,
    private val previousText: String,
    val boldedIndexes: List<Int>,
    private val removedCharacterIndex: Int = INVALID_INDEX,
    private val wasRemovedCharacterBolded: Boolean = false,
) {
    companion object {
        const val INVALID_INDEX = -1
        const val TAG = "BOLDED_REMOVER:"
    }


    //For using the chaining we return the new instance of the class
    //by updating data
    fun findRemovedCharacterIndex(): BoldedIndexRemover {
        val isMultipleCharacterRemoved = (previousText.length - currentText.length != 1)
        if (isMultipleCharacterRemoved) {
            //    println("Removed From:${INVALID_INDEX}")
            return this
        }
        for (i in previousText.indices) {
            val isBothStringIthCharacterIsDifferent = previousText[i] != currentText.getOrNull(i)
            if (isBothStringIthCharacterIsDifferent) {
                //  println("Removed From:${i}")
                return this.copy(removedCharacterIndex = i)
            }
        }
        // println("Removed From:${INVALID_INDEX}")
        // No difference found
        return this

    }

    fun checkRemovedCharacterWasBolded(): BoldedIndexRemover {
        //
        val isRemovedIndexPresentInBoldedIndexSet =
            boldedIndexes.contains(removedCharacterIndex)
        //  println("Removed Char was Bolded:${isRemovedIndexPresentInBoldedIndexSet}")
        return this.copy(wasRemovedCharacterBolded = isRemovedIndexPresentInBoldedIndexSet)
    }

    fun removeIndexFromBoldedListIfPresent(): BoldedIndexRemover {
        if (!wasRemovedCharacterBolded)
            return this

        //after removing the index,shift the remaining index by 1 left
        //filter the negative index
        val updatedBoldedIndexes = boldedIndexes
            .filterNot { it == removedCharacterIndex }
            .toMutableList()
        // println("Updated Indices:${updatedBoldedIndexes}")
        Log.i(
            "BOLDED_REMOVER:SE",
            "${previousText}\n${currentText}\n$boldedIndexes\n$updatedBoldedIndexes"
        )
        return this.copy(boldedIndexes = updatedBoldedIndexes)
    }


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

fun isCharAddedBefore(original: String, modified: String, i: Int): Boolean {
    if (modified.length <= original.length || i < 0 || i >= original.length) {
        return false
    }
    return original.substring(0, i) != modified.substring(0, i)
}

fun isCharacterRemovedBeforeIndex(
    previousText: String,
    currentString: String,
    index: Int,
): Boolean {
    if (currentString.length < previousText.length) {
        val previousChar = if (index > 0) previousText[index - 1] else null
        val newChar = if (index > 0) currentString[index - 1] else null
        if (previousChar != newChar) {
            return true
        }
    }
    return false
}


//    val formatter = VisualTransformation { textSnapshot ->
//        val transformedText = buildAnnotatedString {
//            with(textSnapshot) {
//                text.forEachIndexed { index, char ->
//                    val boldIndex = boldIndices.indexOf(index)
//                    val fontWeight =
//                        if (boldIndex != -1) FontWeight.Bold else FontWeight.Normal
//                    withStyle(style = SpanStyle(fontWeight = fontWeight)) {
//                        append(char)
//                    }
//                }
//            }
//        }
//
//        val offsetMapping = object : OffsetMapping {
//            override fun originalToTransformed(offset: Int): Int {
//                return offset
//            }
//
//            override fun transformedToOriginal(offset: Int): Int {
//                return offset
//            }
//        }
//
//        TransformedText(transformedText, offsetMapping)
//    }
