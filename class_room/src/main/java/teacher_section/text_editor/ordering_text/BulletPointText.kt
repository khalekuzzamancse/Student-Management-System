package teacher_section.text_editor.ordering_text

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
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


@Preview
@Composable
fun EditText() {
    var textFieldText by remember { mutableStateOf(TextFieldValue()) }
    Column(modifier = Modifier.padding(8.dp)) {
        TextEditorTopSection(
            onBulletListClick = {
                val start = textFieldText.selection.start
                val end = textFieldText.selection.end
                var text = textFieldText.text
                text = OrderTextConverter(text, start, end).formatWithBullet()
                textFieldText = TextFieldValue(text)
            },
            onNumberListClick = {
                val start = textFieldText.selection.start
                val end = textFieldText.selection.end
                var text = textFieldText.text
                text = OrderTextConverter(text, start, end).formatWithNumber()
                textFieldText = TextFieldValue(text)
            },
            onFormatClearClick = {
                val start = textFieldText.selection.start
                val end = textFieldText.selection.end
                var text = textFieldText.text
                text = OrderTextConverter(text, start, end).clearFormat()
                textFieldText = TextFieldValue(text)
            },
            onBoldIconClick = {},
            onItalicIconClick = {},
            onUnderLineIconClick = {},
            onTextColorChangeIconClick = {},
            onLineThroughIconClick = {},
        )
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .border(width = 1.dp, color = Color.Black)
                .padding(16.dp),
            value = textFieldText,
            onValueChange = {
                textFieldText = it
            },
            textStyle = MaterialTheme.typography.displaySmall
        )

    }
}



