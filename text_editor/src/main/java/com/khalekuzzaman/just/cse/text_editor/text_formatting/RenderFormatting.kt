package com.khalekuzzaman.just.cse.text_editor.text_formatting

import android.util.Log
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
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

class RenderFormatting(
    private val formatterMap: TreeMap<Int, Set<Formatter>>,
    private val text: AnnotatedString,
) {
    fun format(): AnnotatedString {
        val annotatedString = buildAnnotatedString {
            append(text)
            formatterMap.forEach { (index, formatters) ->
                formatters.forEach {
                    if (index !in text.indices) {
                        Log.i(
                            "RenderFormatting:format()", "format():The index($index)" +
                                    " is out of range(0-${text.length - 1}"
                        )
                    } else {
                        addStyle(
                            it.getStyle(),
                            start = index, end = index + 1
                        )
                    }

                }

            }

        }
        return annotatedString
    }

}
