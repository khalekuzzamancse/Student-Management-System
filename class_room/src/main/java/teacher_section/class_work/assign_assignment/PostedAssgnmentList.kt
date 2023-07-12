package teacher_section.class_work.assign_assignment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


data class AssignedAssignment(
    val title: String,
    val date: String,
)

@Preview
@Composable
private fun Preview() {
    val list = listOf(
        AssignedAssignment(
            title = "Introduction to Algorithms",
            date = "Yesterday  day"
        ),
        AssignedAssignment(
            title = "Introduction to Algorithms",
            date = "Yesterday  day"
        ), AssignedAssignment(
            title = "Introduction to Algorithms",
            date = "Yesterday  day"
        )
    )
    AssignedAssignments(
        list = list
    )
}

@Composable
fun AssignedAssignments(
    list: List<AssignedAssignment>,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        list.forEach {
            ListItem(
                title = it.title,
                date = it.date
            )
        }
    }


}

@Composable
private fun ListItem(
    modifier: Modifier = Modifier,
    title: String,
    date: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.Blue),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Assignment,
                contentDescription = null,
                modifier = Modifier.background(Color.White)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = date)
        }
    }


}