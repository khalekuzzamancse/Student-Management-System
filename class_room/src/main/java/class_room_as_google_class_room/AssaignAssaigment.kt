package class_room_as_google_class_room

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ControlPoint
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Dock
import androidx.compose.material.icons.filled.DriveEta
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Topic
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.common_ui_element.input_field.CustomDatePicker
import com.khalekuzzaman.just.cse.common_ui_element.input_field.OutlinedTextInputField

@Preview
@Composable
private fun AssignAssignmentPreview() {
    AssignAssignment(modifier = Modifier.padding(8.dp))
}

@Composable
fun AssignAssignment(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextInputField(
            label = "Assignment title",
            onTextChanged = {}
        )
        OutlinedTextInputField(
            label = "Description",
            onTextChanged = {},
            icon = Icons.Default.Description
        )
        OutlinedTextInputField(
            label = "Mark",
            keyboardType = KeyboardType.Number,
            onTextChanged = {},
            icon = Icons.Default.ControlPoint
            )
        CustomDatePicker(
            "Due date",
            icon = Icons.Default.DateRange,


            )
        OutlinedTextInputField(
            label = "Topic",
            onTextChanged = {},
            icon = Icons.Default.List
        )
    }

}