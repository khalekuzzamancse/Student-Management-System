package teacher_section.text_editor

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle

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
fun isCharacterRemovedBeforeIndex(previousText: String, currentString: String, index: Int): Boolean {
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
