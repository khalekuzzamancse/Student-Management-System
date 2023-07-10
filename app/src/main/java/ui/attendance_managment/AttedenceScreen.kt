package ui.attendance_managment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AttendanceScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        var show by remember {
            mutableStateOf(true)
        }

        val showSummary: () -> Unit = {
            show = false
        }
        val showAttendanceSheet: () -> Unit = {
            show = true
        }

        if (show) {
            Take(onSummaryButtonClick = showSummary)
        } else {
            Summary(onCrossButtonClick = showAttendanceSheet)
        }

    }

}

@Composable
fun BoxScope.Take(
    modifier: Modifier = Modifier,
    onSummaryButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .matchParentSize()

    ) {
        Button(onClick = onSummaryButtonClick) {
            Text(text = "Summary")
        }
        AttendanceSheet(attendances)

    }
}

@Composable
fun BoxScope.Summary(
    modifier: Modifier = Modifier,
    onCrossButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .matchParentSize()
    ) {
        Button(onClick = onCrossButtonClick) {
            Icon(
                imageVector = Icons.Default.Cancel,
                contentDescription = null
            )
        }
        ResultSummary(
            modifier = Modifier,
            totalClass = 16,
            data = ResultSummaryFakeData.getData()
        )

    }
}


