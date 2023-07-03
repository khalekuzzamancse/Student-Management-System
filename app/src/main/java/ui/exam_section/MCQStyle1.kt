package ui.exam_section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class MCQOptions { A, B, C, D, None }

data class MCQ(
    val question: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    val answer: MCQOptions,
)
@Preview
@Composable
private fun Preview() {
    val mcq = MCQ(
        question = "What is the name of your country ?",
        optionA = "Bangladesh",
        optionB = "India",
        optionC = "Nepal",
        optionD = "Pakistan",
        answer = MCQOptions.B
    )
    MCQ(mcq = mcq)

}
@Composable
fun MCQ(modifier: Modifier = Modifier, mcq: MCQ) {

    Column(modifier.fillMaxWidth()) {

        Text(text = mcq.question)
        MCQOptionsRow(
            optionName = "A )",
            optionalDetails = mcq.optionA,
        )
        MCQOptionsRow(
            optionName = "B )",
            optionalDetails = mcq.optionB,

            )
        MCQOptionsRow(
            optionName = "C )",
            optionalDetails = mcq.optionC,

            )
        MCQOptionsRow(
            optionName = "D )",
            optionalDetails = mcq.optionD,

            )
        AnswerRow()
    }

}

@Composable
private fun AnswerRow(modifier: Modifier = Modifier) {
    var optionSelected by remember {
        mutableStateOf(MCQOptions.None)
    }
    val updateSelection: (MCQOptions) -> Unit = { option ->
        optionSelected = if (optionSelected != option)
            option
        else {
            //Remove selection
            MCQOptions.None
        }
    }
    Row(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "A")
            RadioButton(
                selected = optionSelected == MCQOptions.A,
                onClick = { updateSelection(MCQOptions.A) }
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "B")
            RadioButton(
                selected = optionSelected == MCQOptions.B,
                onClick = { updateSelection(MCQOptions.B) }
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "C")
            RadioButton(
                selected = optionSelected == MCQOptions.C,
                onClick = { updateSelection(MCQOptions.C) }
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "D")
            RadioButton(
                selected = optionSelected == MCQOptions.D,
                onClick = { updateSelection(MCQOptions.D) }
            )
        }

    }
}


@Composable
private fun MCQOptionsRow(
    modifier: Modifier = Modifier,
    optionName: String,
    optionalDetails: String,
) {

    Row(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = optionName)
        Spacer(modifier = Modifier.width(8.dp))
        Text(modifier = Modifier.weight(1f), text = optionalDetails)
    }
}
