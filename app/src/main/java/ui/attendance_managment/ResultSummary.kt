package ui.attendance_managment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ui.table.TextualTable

@Preview
@Composable
fun ResultSummaryPreview() {
    Box(modifier = Modifier.fillMaxWidth()) {
        ResultSummary(
            modifier = Modifier,
            totalClass = 16,
            data = ResultSummaryFakeData.getData()
        )
    }

}

object ResultSummaryFakeData {
    private val data = mutableListOf<AttendanceSummaryRow>()
    fun getData(): List<AttendanceSummaryRow> {
        for (i in 1..40) {
            data.add(
                AttendanceSummaryRow(
                    studentId = i,
                    todayPresent = 1,
                    totalAbsent = 10,
                    totalPresent = 8
                )
            )
        }
        return data
    }
}

data class AttendanceSummaryRow(
    val studentId: Int,
    val todayPresent: Int,
    val totalPresent: Int,
    val totalAbsent: Int,
) {
    fun getTableRowData(): List<String> {
        return listOf(
            studentId.toString(),
            todayPresent.toString(),
            totalPresent.toString(),
            totalAbsent.toString(),
        )
    }
}


@Composable
fun ResultSummary(
    modifier: Modifier = Modifier,
    totalClass: Int,
    data: List<AttendanceSummaryRow>,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()

    ) {

        Text("TotalClass =$totalClass")
        val list= mutableListOf<List<String>>()
        data.forEach {
            list.add(it.getTableRowData())
        }

        TextualTable(
            listOf(
                "Student Id",
                "Today Present",
                "Total Present",
                "Total Absent"
            ),
            data =list
        )
    }


}

