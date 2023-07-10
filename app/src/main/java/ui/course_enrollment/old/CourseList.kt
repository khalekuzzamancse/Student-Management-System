package ui.course_enrollment.old

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CourseList() {
    val class7courses = listOf(
        CheckboxItem(label = "Anondo Path", icon = Icons.Default.HistoryEdu),
        CheckboxItem(label = "English for Today", icon = Icons.Default.HistoryEdu),
        CheckboxItem(label = "Bangla Grammar", icon = Icons.Default.HistoryEdu),
        CheckboxItem(label = "Agriculture", icon = Icons.Default.HistoryEdu),
        CheckboxItem(label = "Grahorsto", icon = Icons.Default.HistoryEdu),
        CheckboxItem(label = "Physical Education", icon = Icons.Default.HistoryEdu),
        CheckboxItem(label = "ICT", icon = Icons.Default.HistoryEdu),
        CheckboxItem(label = "Cristian Religious", icon = Icons.Default.HistoryEdu),
        CheckboxItem(label = "General Science", icon = Icons.Default.HistoryEdu),
        CheckboxItem(label = "Islam Religious", icon = Icons.Default.HistoryEdu),
        CheckboxItem(label =  "Buddhist", icon = Icons.Default.HistoryEdu),
        CheckboxItem(label =  "Art", icon = Icons.Default.HistoryEdu),
        CheckboxItem(label =  "English Grammar", icon = Icons.Default.HistoryEdu),
        CheckboxItem(label =  "Mathematics", icon = Icons.Default.HistoryEdu),
        )
    TriStateCheckBox(class7courses)
    //hi
}

@Preview
@Composable
private fun Preview() {
    CourseList()
}