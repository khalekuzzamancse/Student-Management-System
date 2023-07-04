package ui.math_writer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SuperScript(
) {

    Column() {
        val text = buildAnnotatedString {
            withStyle(
                SpanStyle()
            ) {
                append("H")
            }
            withStyle(
                SpanStyle(baselineShift = BaselineShift.Subscript)
            ) {
                append("2")
            }
            withStyle(
                SpanStyle()
            ) {
                append("O")
            }
        }
        Text(text)
        val subScriptStyle = SpanStyle(baselineShift = BaselineShift.Subscript)
        val baseText = AnnotatedString("H")
        val subScriptText = AnnotatedString(
            text = "2",
            spanStyle = subScriptStyle
        )
        val remainingBaseText = AnnotatedString("O")
        Text(text = baseText + subScriptText + remainingBaseText)
    }


}

@Preview
@Composable
private fun Preview() {

    val textMeasurer = rememberTextMeasurer()
    val text = "90"
    val layoutResult = textMeasurer.measure(text, style = TextStyle.Default.copy(fontSize = 20.sp))
    val textHeight = layoutResult.size.height
    val textWidth = layoutResult.size.width
    // do something with textHeight
    Canvas(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        drawLine(
            start = Offset(0f, textHeight.toFloat()),
            end = Offset(textWidth.toFloat(), textHeight.toFloat()),
            color = Color.Black
        )
        drawText(
            topLeft = Offset(0f, 0f),
            textMeasurer = textMeasurer, text = text
        )
        drawText(
            topLeft = Offset(0f, textHeight.toFloat()),
            textMeasurer = textMeasurer, text = "45"
        )
    }

}