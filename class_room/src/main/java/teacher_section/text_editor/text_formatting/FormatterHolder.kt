package teacher_section.text_editor.text_formatting

import androidx.compose.ui.text.TextRange


enum class TextStyleType {
    BOLD, RED, ITALIC,UNDERLINE,LINE_THROUGH
}


/*
TextStyleInfo and  TextStyleManager class is close for modification.
need to edit the source code of them  when added a new formatting type.
 */

data class TextStyleInfo(
    val type: TextStyleType,
    val indices: List<Int>,
    val formatter: CharacterFormatter,
)


class TextStyleManager(
    private val textStylesWithIndices: List<TextStyleInfo>,
) {

    fun createFormatters(): List<Formatter> {
        return textStylesWithIndices.map { textStyle ->
            Formatter(
                indices = textStyle.indices,
                formatter = textStyle.formatter
            )
        }
    }


    fun onTextChanges(currentText: String, previousText: String) =
        updateAllFormattersIndices(currentText, previousText)

    private fun updateAllFormattersIndices(
        currentText: String,
        previousText: String,
    ): TextStyleManager {
        val newTextStyles = textStylesWithIndices.map { textStyle ->
            val newIndices = FormattedIndicesUpdater.updateFormattedIndices(
                currentText = currentText,
                previousText = previousText,
                formattedIndices = textStyle.indices
            )
            textStyle.copy(indices = newIndices)
        }
        return TextStyleManager(newTextStyles)
    }

    fun formatSelectedText(textStyleType: TextStyleType, selectedTextRange: TextRange) =
        updateIndicesForSelection(
            textStyleType,
            selectedTextRange
        )

    private fun updateIndicesForSelection(
        textStyleType: TextStyleType,
        selectedTextRange: TextRange,
    ): TextStyleManager {
        val newTextStyles = textStylesWithIndices.map { textStyle ->
            if (textStyle.type == textStyleType) {
                val newIndices = FormattedIndicesUpdater.updateFormattedIndicesWithSelection(
                    selectedTextRange = selectedTextRange,
                    formattedIndices = textStyle.indices.toMutableList()
                )
                textStyle.copy(indices = newIndices)
            } else {
                textStyle
            }
        }
        return TextStyleManager(newTextStyles)
    }

}