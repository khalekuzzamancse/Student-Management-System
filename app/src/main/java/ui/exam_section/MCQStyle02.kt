package ui.exam_section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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


@Preview
@Composable
fun MCQStyle2() {
    val mcqList = listOf(
        MCQ(
            question = "What is the name of your country ?",
            optionA = "Bangladesh",
            optionB = "India",
            optionC = "Nepal",
            optionD = "Pakistan",
            answer = MCQOptions.B
        ), MCQ(
            question = "Why the national food of bangladesh ?",
            optionA = "Jack Fruit",
            optionB = "Banana",
            optionC = "Mango",
            optionD = "Apple",
            answer = MCQOptions.B
        )
    )
    MCQQuestions(questions = mcqList)
}

@Composable
fun MCQQuestions(modifier: Modifier = Modifier, questions: List<MCQ>) {
    Column(modifier = modifier.padding(8.dp)
    ) {
        questions.forEachIndexed { index, mcq ->
            Row(modifier = Modifier.fillMaxWidth()) {
                val questionNo = index + 1;
                Text(text = "$questionNo : ")
                MCQStyle2(modifier = Modifier.weight(1f), mcq = mcq)
            }

        }
    }
}

@Composable
fun MCQStyle2(modifier: Modifier = Modifier, mcq: MCQ) {
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
    Column(modifier.fillMaxWidth()) {
        Text(text = mcq.question)
        MCQOptionsRow(
            optionName = "A )",
            optionalDetails = mcq.optionA,
            isSelected = optionSelected == MCQOptions.A,
            onOptionSelected = {
                updateSelection(MCQOptions.A)
            }
        )
        MCQOptionsRow(
            optionName = "B )",
            optionalDetails = mcq.optionB,
            isSelected = optionSelected == MCQOptions.B,
            onOptionSelected = {
                updateSelection(MCQOptions.B)
            }
        )
        MCQOptionsRow(
            optionName = "C )",
            optionalDetails = mcq.optionC,
            isSelected = optionSelected == MCQOptions.C,
            onOptionSelected = {
                updateSelection(MCQOptions.C)
            }
        )
        MCQOptionsRow(
            optionName = "D )",
            optionalDetails = mcq.optionD,
            isSelected = optionSelected == MCQOptions.D,
            onOptionSelected = {
                updateSelection(MCQOptions.D)
            }
        )

    }

}

@Composable
private fun MCQOptionsRow(
    modifier: Modifier = Modifier,
    optionName: String,
    optionalDetails: String,
    onOptionSelected: () -> Unit,
    isSelected: Boolean = false,
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
        RadioButton(
            selected = isSelected,
            onClick = onOptionSelected
        )

    }
}
