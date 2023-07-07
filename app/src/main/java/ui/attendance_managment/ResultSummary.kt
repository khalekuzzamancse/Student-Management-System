package ui.attendance_managment

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

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

@Composable
fun BoxScope.ResultSummary(
    modifier: Modifier = Modifier,
    totalClass: Int,
    studentsId: List<Attendance>,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        EachRow(
            _1stCellText = "Student Id",
            _2ndCellText = "Today Present",
            _3rdCellText = "Total Present",
            _4thCellText = "Total Absent",
            _5thCellText = "Percentage"
        )

    }


}

@Composable
private fun EachRow(
    modifier: Modifier = Modifier,
    _1stCellText: String,
    _2ndCellText: String,
    _3rdCellText: String,
    _4thCellText: String,
    _5thCellText: String,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.weight(1f),
            text = _1stCellText
        )
        Text(
            modifier = Modifier.weight(1f),
            text = _2ndCellText
        )
        Text(
            modifier = Modifier.weight(1f),
            text = _3rdCellText
        )
        Text(
            modifier = Modifier.weight(1f),
            text = _4thCellText
        )
        Text(
            modifier = Modifier.weight(1f),
            text = _5thCellText
        )
    }
}