package com.khalekuzzaman.just.cse.text_editor.version_02

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

import java.util.TreeMap

class RenderFormatting(
    private val formatterMap: TreeMap<Int, Set<Formatter>>,
    private val text: AnnotatedString,
) {
    fun format(): AnnotatedString {
        val annotatedString = buildAnnotatedString {
            append(text)
            formatterMap.forEach { (index, formatters) ->
                formatters.forEach {
                    addStyle(
                        it.getStyle(),
                        start = index, end = index + 1
                    )
                }

            }
        }
        return annotatedString
    }

}
