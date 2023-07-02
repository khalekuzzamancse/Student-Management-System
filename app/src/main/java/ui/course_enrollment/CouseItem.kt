package ui.course_enrollment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.BookOnline
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TriStateCheckBox() {
    var checkBox1State by remember {
        mutableStateOf(true)
    }
    var checkBox2State by remember {
        mutableStateOf(true)
    }
    var checkBox3State by remember {
        mutableStateOf(true)
    }

    val parentCheckBoxState = remember(checkBox1State, checkBox2State, checkBox3State) {
        if (checkBox1State && checkBox2State && checkBox3State)
            ToggleableState.On
        else if (!checkBox1State && !checkBox2State && !checkBox3State)
            ToggleableState.Off
        else
            ToggleableState.Indeterminate
    }

    Column {
        Row {
            Text("Parent-CheckBox")
            TriStateCheckbox(
                state = parentCheckBoxState,
                onClick = {
                    val state = parentCheckBoxState != ToggleableState.On
                    checkBox1State = state
                    checkBox2State = state
                    checkBox3State = state
                }
            )

        }

        CourseItem(
            isChecked = checkBox1State,
            label = "Englig",
            icon = Icons.Default.HistoryEdu,
            onCheckChanged = {}
        )
        CourseItem(
            isChecked = checkBox2State,
            label = "Math",
            icon = Icons.Default.HistoryEdu,
            onCheckChanged = {}
        )
        CourseItem(
            isChecked = checkBox3State,
            label = "Math",
            icon = Icons.Default.Book,
            onCheckChanged = {}
        )

    }
}

@Composable
fun CourseItem(
    isChecked:Boolean,
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onCheckChanged: (Boolean) -> Unit,
    label: String,
) {
    var state by remember {
        mutableStateOf(isChecked)
    }
    Row(modifier = modifier.fillMaxWidth()) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
        Text(
            modifier = Modifier.weight(1f),
            text = label
        )
        Checkbox(
            checked = state,
            onCheckedChange = {
                state = !state
                onCheckChanged(state)
            }
        )
    }

}

@Preview
@Composable
private fun Preview() {
    TriStateCheckBox()
}
