package teacher_section.text_editor

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import teacher_section.text_editor.editor_ui.TextEditorTopSection
import teacher_section.text_editor.text_formatting.CharacterFormatter
import teacher_section.text_editor.text_formatting.TextEditorVisualTransformer
import teacher_section.text_editor.text_formatting.TextStyleInfo
import teacher_section.text_editor.text_formatting.TextStyleManager
import teacher_section.text_editor.text_formatting.TextStyleType


val getTextStyleManager = TextStyleManager(
    listOf(
        TextStyleInfo(
            TextStyleType.BOLD,
            listOf(),
            CharacterFormatter.BoldFormatter
        ),
        TextStyleInfo(
            TextStyleType.RED,
            listOf(),
            CharacterFormatter.RedColorFormatter
        ),
        TextStyleInfo(
            TextStyleType.ITALIC,
            listOf(),
            CharacterFormatter.ItalicFormatter
        ),
        TextStyleInfo(
            TextStyleType.UNDERLINE,
            listOf(),
            CharacterFormatter.UnderLineFormatter
        ),
        TextStyleInfo(
            TextStyleType.LINE_THROUGH,
            listOf(),
            CharacterFormatter.LineThroughFormatter
        )
    )
)

@Preview
@Composable
fun TextEditorVersion01() {
    var textFieldText by remember { mutableStateOf(TextFieldValue("012345678910111213")) }
    var previousText by remember { mutableStateOf("") }
    var textStyleManager by remember { mutableStateOf(getTextStyleManager) }

    val formatters = textStyleManager.createFormatters()
    val visualTransformation = TextEditorVisualTransformer()
        .createTextFormatter(formatters = formatters)


    /*

     */
    val boldText: () -> Unit = {
        textStyleManager = textStyleManager
            .formatSelectedText(TextStyleType.BOLD, textFieldText.selection)
    }
    val italicText: () -> Unit = {
        textStyleManager = textStyleManager
            .formatSelectedText(TextStyleType.ITALIC, textFieldText.selection)
    }
    val redText: () -> Unit = {
        textStyleManager = textStyleManager
            .formatSelectedText(TextStyleType.RED, textFieldText.selection)
    }
    val underLineText: () -> Unit = {
        textStyleManager = textStyleManager
            .formatSelectedText(TextStyleType.UNDERLINE, textFieldText.selection)
    }
    val lineThrough: () -> Unit = {
        textStyleManager = textStyleManager
            .formatSelectedText(TextStyleType.LINE_THROUGH, textFieldText.selection)
    }
    /*

     */
    Column(modifier = Modifier.padding(8.dp)) {
        TextEditorTopSection(
            onBulletListClick = {},
            onNumberListClick = {},
            onFormatClearClick = {},
            onBoldIconClick = boldText,
            onItalicIconClick = italicText,
            onUnderLineIconClick = underLineText,
            onTextColorChangeIconClick = redText,
            onLineThroughIconClick = lineThrough,
        )
        BasicTextField(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth()
                .height(300.dp)
                .border(width = 1.dp, color = Color.Black)
                .padding(16.dp),
            value = textFieldText,
            onValueChange = { currentText ->
                textFieldText = currentText
                textStyleManager = textStyleManager.onTextChanges(currentText.text, previousText)
                previousText = currentText.text

            },
            visualTransformation = visualTransformation
        )

    }
}