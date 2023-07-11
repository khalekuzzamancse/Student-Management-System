import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import class_room_as_google_class_room.Instructions
import class_room_as_google_class_room.StudentWorkList

enum class Tabs {
    Instructions, StudentWork
}
@Preview
@Composable
fun ViewAssignmentDetails() {
    var showTab by remember {
        mutableStateOf(Tabs.Instructions)
    }
    Column(modifier = Modifier
        .fillMaxWidth()){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                showTab = Tabs.Instructions
            }) {
                Text("Instruction")
            }
            Button(onClick = {
                showTab = Tabs.StudentWork
            }) {
                Text("Student work")
            }

        }
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