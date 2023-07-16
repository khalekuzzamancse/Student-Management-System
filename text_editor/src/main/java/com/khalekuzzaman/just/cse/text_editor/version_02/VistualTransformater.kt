package com.khalekuzzaman.just.cse.text_editor.version_02

import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.util.TreeMap

class EditorVisualTransformer {
    fun createTextFormatter(
        formatterMap: TreeMap<Int, Set<Formatter>>,
    ): VisualTransformation {
        val visualTransformation =
            VisualTransformation { fieldText ->
                val transformedText = RenderFormatting(formatterMap, fieldText).format()
                TransformedText(transformedText, TextEditorOffsetMapper.offsetMapping)
            }
        return visualTransformation
    }

}


private object TextEditorOffsetMapper {
    val offsetMapping = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return offset
        }

        override fun transformedToOriginal(offset: Int): Int {
            return offset
        }
    }
}