package teacher_section.text_editor

import android.util.Log
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
import androidx.compose.material.icons.filled.FormatClear
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
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
        TextEditorTopSection(
            onBulletListClick = {
                val start = textFieldText.selection.start
                val end = textFieldText.selection.end
                var text = textFieldText.text
                text= OrderTextConverter(text, start, end).formatWithBullet()
             textFieldText=TextFieldValue(text)
            },
            onNumberListClick = {
                val start = textFieldText.selection.start
                val end = textFieldText.selection.end
                var text = textFieldText.text
                text= OrderTextConverter(text, start, end).formatWithNumber()
                textFieldText= TextFieldValue(text)
            },
            onFormatClearClick = {
                val start = textFieldText.selection.start
                val end = textFieldText.selection.end
                var text = textFieldText.text
                text= OrderTextConverter(text, start, end).clearFormat()
                textFieldText= TextFieldValue(text)
            },
        )
        TextField(
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


@Composable
fun TextEditorTopSection(
    modifier: Modifier = Modifier,
    onBulletListClick: () -> Unit,
    onNumberListClick: () -> Unit,
    onFormatClearClick: () -> Unit,
) {
    Surface(
        //   shadowElevation = 5.dp,
        tonalElevation = 5.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = onBulletListClick) {
                Icon(
                    imageVector = Icons.Default.FormatListBulleted,
                    contentDescription = null
                )
            }
            IconButton(onClick = onNumberListClick) {
                Icon(
                    imageVector = Icons.Default.FormatListNumbered,
                    contentDescription = null
                )
            }
            IconButton(onClick = onFormatClearClick) {
                Icon(
                    imageVector = Icons.Default.FormatClear,
                    contentDescription = null
                )
            }
        }
    }


}

