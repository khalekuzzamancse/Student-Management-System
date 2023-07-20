package com.khalekuzzaman.just.cse.text_editor.equation_writer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MathEquationRepresentation(equation: String) {
    val parts = equation.split("/")
    if (parts.size == 2) {
        Column {
            TextWithStyle(parts[0], size = 24.sp)
            TextWithStyle("--".repeat(parts[0].length), size = 20.sp, color = Color.Black)
            TextWithStyle(parts[1], size = 24.sp)
        }
    } else {
        TextWithStyle(equation, size = 24.sp)
    }
}

@Composable
fun TextWithStyle(
    text: String,
    size: androidx.compose.ui.unit.TextUnit,
    color: Color = Color.Black,
) {
    Text(
        text = AnnotatedString(text),
        style = TextStyle(fontSize = size, color = color),
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Preview
@Composable
fun PreviewMathEquationRepresentation() {
    Surface {
        MathEquationRepresentation("25/10")
    }

}
