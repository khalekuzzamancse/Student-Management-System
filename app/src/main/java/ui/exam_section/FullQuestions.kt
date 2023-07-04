package ui.exam_section

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ui.drawing_board.WhiteBoard

@Preview
@Composable
private fun Preview() {
    FullQuestions()
}
@Composable
fun FullQuestions() {
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
        ), MCQ(
            question = "What is your favorite subject?",
            optionA = "Bangla",
            optionB = "English",
            optionC = "Math",
            optionD = "Physics",
            answer = MCQOptions.B
        )
    )
    val questions = listOf(
        WrittenQuestion(question = "What is your country name ?", marks = 10),
        WrittenQuestion(question = "What is your country name ?", marks = 10),
    )
    var showWhiteboard by remember {
        mutableStateOf(false)
    }


    Box(modifier=Modifier.fillMaxSize()) {
        if (showWhiteboard) {
                WhiteBoard(
                    modifier = Modifier.matchParentSize(),
                    onDismissButtonClick = { showWhiteboard = false })

        } else {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                WrittenQuestions(
                    modifier = Modifier.wrapContentHeight(),
                    questions = questions,
                    onAnswerButtonClick = { showWhiteboard = true }
                )
                MCQQuestions(
                    modifier = Modifier.wrapContentHeight(),
                    questions = mcqList
                )
            }
        }


    }

}