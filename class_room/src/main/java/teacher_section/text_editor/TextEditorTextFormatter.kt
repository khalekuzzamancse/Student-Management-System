package teacher_section.text_editor

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString


data class TextEditorTextFormatter(
    private val formatter: List<Formatter>,
    private val text: AnnotatedString,
) {
    fun format(): AnnotatedString {
        val annotatedString = buildAnnotatedString {
            append(text)
            text.forEachIndexed { index, c ->
                formatter.forEach {
                    if (shouldThisIndexBeFormatted(it.indices, index)) {
                        addStyle(
                            it.formatter.getStyle(),
                            start = index, end = index + 1
                        )
                    }
                }
            }
        }
        return annotatedString
    }
    private fun shouldThisIndexBeFormatted(
        formattedIndices: List<Int>,
        index: Int,
    ): Boolean {
        val formattedIndex = formattedIndices.indexOf(index)
        return formattedIndex != -1
    }

}




data class Formatter(
    val indices: List<Int>,
    val formatter: CharacterFormatter,
)