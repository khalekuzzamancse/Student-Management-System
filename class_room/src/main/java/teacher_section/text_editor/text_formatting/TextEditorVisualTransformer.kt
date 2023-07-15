package teacher_section.text_editor.text_formatting

import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation


class TextEditorVisualTransformer {
    fun createTextFormatter(
        formatters: List<Formatter>,
    ): VisualTransformation {
        val visualTransformation =
            VisualTransformation {fieldText->
                val transformedText = TextEditorTextFormatter(formatters,fieldText).format()
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