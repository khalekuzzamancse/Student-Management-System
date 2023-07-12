package teacher_section.class_work.view_asignment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material.icons.filled.Message
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun Preview() {
    Instructions(
        modifier = Modifier.padding(8.dp),
        dueDate = "Today",
        title = "Assignment 01",
        marks = "10",
        description = "Write a short on: \nClass in Java.\nObject in Java."
    )
}

@Composable
fun Instructions(
    modifier: Modifier = Modifier,
    dueDate: String,
    title: String,
    marks: String,
    description: String,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = "Due date : $dueDate")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            color = Color.Blue,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            style = MaterialTheme.typography.bodyLarge,
            text = "$marks marks"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Message,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Add class comment")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.Blue,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            style = MaterialTheme.typography.bodyLarge,
            text = description
        )
    }
}

