package teacher_section.class_work.assign_assignment

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
    Column() {
        val bullet = '•'
        TextField(
            value = textFieldText,
            onValueChange = {
                textFieldText = it
            }
        )
        Button(onClick = {
            textFieldText = TextFieldValue(
                text = UnOrderTextConverted(textFieldText.text)
                    .getModifiedText()
            )

        }) {
            Text(text = "done")
        }
    }


}
