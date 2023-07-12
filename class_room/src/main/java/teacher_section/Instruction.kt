package teacher_section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun Preview() {
    Instructions(
        modifier=Modifier.padding(8.dp),
        dueDate ="Today",
        title ="Assignment 01",
        marks ="10",
        description ="Write a short on: \nClass in Java.\nObject in Java."
    )
}
@Composable
fun Instructions(
    modifier: Modifier=Modifier,
    dueDate:String,
    title:String,
    marks:String,
    description:String,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = "Due date : $dueDate")
        Text(text = title, style = MaterialTheme.typography.titleLarge)
        Text(text = "$marks marks")
        Text(text = description)
    }
}

