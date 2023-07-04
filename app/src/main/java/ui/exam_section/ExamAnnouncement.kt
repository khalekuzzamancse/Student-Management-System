package ui.exam_section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class ExamClass {
    Six, Seven, Eight, Nine, Ten
}

enum class ExamResultStatus {
    Published, Unpublished,NotHeld
}

enum class ExamType {
    FirstTerm, SecondTerm, FinalTerm, ClassTest
}


data class ExamAnnouncement(
    val name: String,
    val id: String,
    val date: String,
    val time: String,
    val year: String,
    val examType: ExamType,
    val resultStatus: ExamResultStatus,
    val includedClasses: List<ExamClass>,
)

@Composable
fun ExamDetails(details: ExamAnnouncement) {
    Slot(
        title = {
            Text(text = details.name)
        },
        examTypeYear = {
            Text(text = details.examType.toString())
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = details.year)
        },
        examSchedule = {
            Text(text = details.time)
        },
        footer = {
            Text(text = "Included Class: "
                    +details.includedClasses.toString())
        }
    )

}

@Composable
fun Slot(
    title: @Composable () -> Unit,
    examTypeYear: @Composable () -> Unit,
    examSchedule: @Composable () -> Unit,
    footer: @Composable () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        title()
        Row {
            Spacer(modifier = Modifier.weight(1f))
            examTypeYear()
        }
        examSchedule()
        footer()
    }

}

@Preview
@Composable
private fun Preview() {
    val exam = ExamAnnouncement(
        name = "Exam 01",
        id = "1",
        date = "02-22-23",
        time = "10:00 AM",
        examType = ExamType.FirstTerm,
        resultStatus = ExamResultStatus.NotHeld,
        year = "2023",
        includedClasses = listOf(
            ExamClass.Six,
            ExamClass.Seven,
            ExamClass.Eight,
            ExamClass.Nine,
            ExamClass.Ten,
        )
    )
    ExamDetails(exam)
}