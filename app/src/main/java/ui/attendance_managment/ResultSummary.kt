package ui.attendance_managment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
            studentsId = attendances
        )
    }

}

object ResultSummaryFakeData {

    private val data = mutableListOf<List<String>>()
    fun getData(): List<List<String>> {
        for (i in 1..40) {
            if (i % 2 == 0)
                data.add(listOf("01", "0", "10", "8"))
            else
                data.add(listOf("01", "1", "10", "8"))
        }
        return data
    }
}

/*
Find why can not make the table horizontal scrollable
I think there is a problem in the row
 */
@Composable
fun ResultSummary(
    modifier: Modifier = Modifier,
    totalClass: Int,
    studentsId: List<Attendance>,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()

    ) {

        TextualTable(
            listOf("Student Id", "Today Present", "Total Present", "Total Absent"),
            data = ResultSummaryFakeData.getData()
        )
    }


}

