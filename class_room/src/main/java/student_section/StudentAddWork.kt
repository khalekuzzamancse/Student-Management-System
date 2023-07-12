package student_section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import teacher_section.class_work.view_asignment.Instructions

@Preview
@Composable
fun AddWorkScreen() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Instructions(
            modifier = Modifier.padding(8.dp),
            dueDate = "Today",
            title = "Assignment 01",
            marks = "10",
            description = "Write a short on: \nClass in Java.\nObject in Java."
        )

    }
}


@Composable
fun AddYourWorkSection(
    modifier: Modifier=Modifier,
    totalMarks: String="",
    obtainedMarks: String="",
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = "Your work")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "$obtainedMarks/$totalMarks")
        }
        Button(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
            Text(text = "Add work")
        }
    }
}