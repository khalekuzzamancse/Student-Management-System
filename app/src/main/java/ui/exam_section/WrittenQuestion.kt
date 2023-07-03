package ui.exam_section

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ui.drawing_board.WhiteBoard

@Preview
@Composable
private fun Preview() {
    val questions = listOf(
        "What is your country name ?",
        "How old are you?",
        "Where you live?",
        "How many children you have?",
    )
    WrittenQuestions(questions = questions)
}

@Composable
fun WrittenQuestions(questions: List<String>) {
    var showWhiteboard by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {

        if (showWhiteboard) {
            Column(modifier = Modifier.matchParentSize()) {
                WhiteBoard(
                    modifier = Modifier.fillMaxSize(),
                    onDismissButtonClick = { showWhiteboard = false})
            }

        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                questions.forEach {
                    WrittenQuestionStyle02(
                        question = it,
                        onAnswerButtonClick = {
                            showWhiteboard = true
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun WrittenQuestionStyle02(
    modifier: Modifier = Modifier,
    onAnswerButtonClick: () -> Unit,
    question: String,
) {
    Column(modifier = modifier) {
        Text(text = question)
        Button(onClick = onAnswerButtonClick) {
            Text(text = "Give Answer")
        }
    }

}