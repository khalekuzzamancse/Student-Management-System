package teacher_section.text_editor.testing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import teacher_section.text_editor.text_formatting.CharacterFormatter
import teacher_section.text_editor.text_formatting.TextEditorVisualTransformer
import teacher_section.text_editor.text_formatting.TextStyleInfo
import teacher_section.text_editor.text_formatting.TextStyleManager
import teacher_section.text_editor.text_formatting.TextStyleType


@Preview
@Composable
private fun TextFormattingV02() {
    var textFieldText by remember { mutableStateOf(TextFieldValue("012345678910111213")) }
    var previousText by remember { mutableStateOf("") }
    var textStyleManager by remember {
        mutableStateOf(
            TextStyleManager(
                listOf(
                    TextStyleInfo(
                        TextStyleType.BOLD,
                        listOf(),
                        CharacterFormatter.BoldFormatter
                    ),
                    TextStyleInfo(
                        TextStyleType.COLOR,
                        listOf(),
                        CharacterFormatter.RedColorFormatter
                    ),
                    TextStyleInfo(
                        TextStyleType.ITALIC,
                        listOf(),
                        CharacterFormatter.ItalicFormatter
                    )
                )
            )
        )
    }


    val formatters = textStyleManager.createFormatters()
    val visualTransformation = TextEditorVisualTransformer()
        .createTextFormatter(formatters = formatters)


    Column(modifier = Modifier.padding(8.dp)) {
        TextField(
            value = textFieldText,
            onValueChange = { currentText ->
                textFieldText = currentText
                textStyleManager = textStyleManager.onTextChanges(currentText.text, previousText)
                previousText = currentText.text

            },
            visualTransformation = visualTransformation
        )
        Spacer(modifier = Modifier.height(100.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                textStyleManager = textStyleManager
               .formatSelectedText(TextStyleType.BOLD, textFieldText.selection)

            }) {
                Text(text = "Bold")
            }

            Button(onClick = {
                textStyleManager = textStyleManager.
                formatSelectedText(TextStyleType.COLOR, textFieldText.selection)
            }) {
                Text(text = "Red Color")
            }
            Button(onClick = {
                textStyleManager = textStyleManager
                .formatSelectedText(TextStyleType.ITALIC, textFieldText.selection)
            }) {
                Text(text = "Italic")
            }
        }

    }

}



