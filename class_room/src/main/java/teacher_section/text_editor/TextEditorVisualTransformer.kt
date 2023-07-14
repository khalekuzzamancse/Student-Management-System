package teacher_section.text_editor

import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation



class TextEditorVisualTransformer {
    fun createTextFormatter(
        formattedIndices: List<Int>,
    ): VisualTransformation {
        val visualTransformation =
            VisualTransformation {
                val textFormatter = TextEditorTextFormatter(
                    text = it,
                    formattedIndices = formattedIndices,
                    characterFormatter = CharacterFormatters.CharacterBoldFormatter
                )
                val transformedText = textFormatter
                    .formatWholeText()
                    .text
                TransformedText(transformedText, TextEditorOffsetMapper.offsetMapping)
            }
        return visualTransformation
    }

}


object TextEditorOffsetMapper {
    val offsetMapping = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return offset
        }

        override fun transformedToOriginal(offset: Int): Int {
            return offset
        }
    }
}