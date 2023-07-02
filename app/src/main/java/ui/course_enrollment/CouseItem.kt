package ui.course_enrollment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TriStateCheckBox(list: List<CheckboxItem>) {

    val listWithAllChecked= list.map { item ->
            item.copy(isChecked = true)
    }

    var checkboxItems by remember {
        mutableStateOf(list)
    }
    val updateList: (CheckboxItem) -> Unit = { checkboxItem ->
        val updatedList = checkboxItems.map { item ->
            if (item == checkboxItem) {
                item.copy(isChecked = !item.isChecked)
            } else {
                item
            }
        }
        checkboxItems = updatedList.toMutableList()
    }

    val parentCheckboxState: ToggleableState = remember(checkboxItems) {
        val allChecked = checkboxItems.all { it.isChecked }
        val allUnchecked = checkboxItems.all { !it.isChecked }

        if (allChecked) ToggleableState.On
        else if (allUnchecked) ToggleableState.Off
        else ToggleableState.Indeterminate
    }

    Column(modifier=Modifier.verticalScroll(rememberScrollState())) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Parent-CheckBox"
            )
            TriStateCheckbox(
                state = parentCheckboxState,
                onClick = {
                    checkboxItems = when(parentCheckboxState){
                        ToggleableState.On-> list
                        ToggleableState.Indeterminate-> list
                        ToggleableState.Off-> listWithAllChecked
                    }
                }
            )
        }


        checkboxItems.forEach { checkboxItem ->
            CourseItem(
                isChecked = checkboxItem.isChecked,
                label = checkboxItem.label,
                icon = checkboxItem.icon,
                onCheckChanged = { updateList(checkboxItem) }
            )
        }
    }
}


data class CheckboxItem(
    val isChecked: Boolean = false,
    val label: String,
    val icon: ImageVector,
)

@Composable
fun CourseItem(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onCheckChanged: () -> Unit,
    label: String,
) {

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
            checked = isChecked,
            onCheckedChange = {
                onCheckChanged()
            }
        )
    }

}


@Preview(
    showSystemUi = true
)
@Composable
private fun Preview() {
    TriStateCheckBox(
        listOf(
            CheckboxItem(label = "English", icon = Icons.Default.HistoryEdu),
            CheckboxItem(label = "Math", icon = Icons.Default.HistoryEdu),
            CheckboxItem(label = "Physics", icon = Icons.Default.HistoryEdu)
        )
    )
}
