import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import teacher_section.Instructions
import teacher_section.StudentWorkList
import teacher_section.view_asignment.StudentWorkTopAppbar

enum class Tabs {
    Instructions, StudentWork
}

@Preview
@Composable
fun ViewAssignmentDetails() {
    var showTab by remember {
        mutableStateOf(Tabs.Instructions)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        StudentWorkTopAppbar(
            onShareButtonClick = {},
            onBackArrowClick = {},
        )
        TabSection(
            onStudentWorkTabClick = {
                showTab = Tabs.StudentWork
            },
            onInstructionTabClick = {
                showTab = Tabs.Instructions
            }
        )
        Divider(modifier = Modifier.fillMaxWidth())
        if (showTab == Tabs.Instructions) {
            Instructions(
                modifier = Modifier.padding(8.dp),
                dueDate = "Today",
                title = "Assignment 01",
                marks = "10",
                description = "Write a short on: \nClass in Java.\nObject in Java."
            )
        } else {
            StudentWorkList()
        }
    }

}


@Composable
private fun TabSection(
    modifier: Modifier = Modifier,
    onInstructionTabClick: () -> Unit,
    onStudentWorkTabClick: () -> Unit,
) {
    var state by remember { mutableIntStateOf(0) }
    val titles = listOf("Instruction", "Student Work")
    TabRow(
        modifier = modifier.fillMaxWidth(),
        selectedTabIndex = state
    ) {
        titles.forEachIndexed { index, title ->
            Tab(
                selected = state == index,
                onClick = {
                    state = index
                    if (state == 0)
                        onInstructionTabClick()
                    else
                        onStudentWorkTabClick()

                },
                text = {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }

    }

}

