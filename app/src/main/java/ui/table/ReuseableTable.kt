package ui.table

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun DynamicTableRowPreview() {
    val composable: @Composable () -> Unit = {
        Text(text = "Hello")
    }
    MyComposableFunction(
        listOf(composable, composable)
    )

}

@Composable
fun getTextToComposable(
    text: String,
    textStyle: TextStyle = TextStyle.Default,
): @Composable () -> Unit = {
    Text(
        style = textStyle,
        text = text
    )
}

@Composable
fun MyComposableFunction(composables: List<@Composable () -> Unit>) {

    for (composable in composables) {
        composable()
    }
}
