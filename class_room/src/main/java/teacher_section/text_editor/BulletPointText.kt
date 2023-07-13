package teacher_section.text_editor

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.FormatListNumberedRtl
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.getSelectedText
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun Preview() {
    BulletList(items = listOf("A", "B", "C"))
}

@Composable
fun BulletList(
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default,
    indent: Dp = 20.dp,
    lineSpacing: Dp = 0.dp,
    items: List<String>,
) {
    Column(modifier = modifier) {
        items.forEach {
            Row {
                //
                val bullet = "•";
                Text(
                    text = bullet,
                    style = style.copy(textAlign = TextAlign.Center),
                    modifier = Modifier.width(indent),
                )
                Text(
                    text = it,
                    style = style,
                    modifier = Modifier.weight(1f, fill = true),
                )
            }
            if (lineSpacing > 0.dp && it != items.last()) {
                Spacer(modifier = Modifier.height(lineSpacing))
            }
        }
    }
}

@Preview
@Composable
fun EditText() {
    var textFieldText by remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier.padding(8.dp)) {
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
            textStyle = MaterialTheme.typography.labelLarge
        )
        Button(onClick = {


            //if text has already bullet point then remove them
            val text = removeBulletPoints(textFieldText.text)

            textFieldText =
                TextFieldValue(
                    UnOrderTextConverted(
                        text = text,
                        start = textFieldText.selection.start,
                        end = textFieldText.selection.end,
                    ).getModifiedText()
                )
        }) {
            Text(text = "done")
        }
        Button(onClick = {
            textFieldText = TextFieldValue(removeBulletPoints(textFieldText.text))
        }) {
            Text(text = "Undo")
        }

    }
}

@Preview
@Composable
fun TextEditorTopSection() {
    Row(modifier = Modifier.fillMaxWidth()) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.FormatListBulleted,
                contentDescription = null
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.FormatListNumbered ,
                contentDescription = null
            )
        }
    }

}

fun removeBulletPoints(text: String): String =
    text.replace('•'.toString(), "")