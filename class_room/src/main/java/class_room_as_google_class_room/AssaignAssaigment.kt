package class_room_as_google_class_room

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.ControlPoint
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.common_ui_element.drop_down_menu.TextualDropDownMenu
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
        TopSection(
            modifier = Modifier,
            onAssignButtonClick = {},
            onCancelButtonClick = {},
        )
        InfoSection(
            modifier=Modifier.verticalScroll(rememberScrollState())
        )

    }

}

@Composable
fun InfoSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextInputField(
            label = "Assignment title",
            onTextChanged = {},
            icon = Icons.Default.Title,
            maxLine = Int.MAX_VALUE,
        )
        OutlinedTextInputField(
            label = "Description",
            onTextChanged = {},
            icon = Icons.Default.Description,
            maxLine = Int.MAX_VALUE,
        )

        OutlinedTextInputField(
            label = "Mark",
            onTextChanged = {},
            icon = Icons.Default.ControlPoint,
            keyboardType = KeyboardType.Number
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

@Composable
private fun TopSection(
    modifier: Modifier = Modifier,
    onCancelButtonClick: () -> Unit,
    onAssignButtonClick: () -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        IconButton(onClick = onCancelButtonClick) {
            Icon(
                imageVector = Icons.Default.Cancel,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = onAssignButtonClick) {
            Text(text = "Assign")
        }
        TextualDropDownMenu(
            menuItems = listOf("Delete", "Schedule"),
            onMenuItemClick = {}
        )
    }

}