package ui.exam_section

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ui.drawing_board.WhiteBoard

data class WrittenQuestion(
    val question: String,
    val marks: Int,
)

@Preview
@Composable
private fun Preview() {
    val questions = listOf(
        WrittenQuestion(question = "What is your country name ?", marks = 10),
        WrittenQuestion(question = "What is your country name ?", marks = 10),
    )
    WrittenQuestions(questions = questions, onAnswerButtonClick = {})
}

@Composable
fun WrittenQuestions(modifier: Modifier=Modifier,onAnswerButtonClick:()->Unit,questions: List<WrittenQuestion>) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                questions.forEachIndexed {index,it->
                    val questionNo=index+1
                    Row(modifier=Modifier.fillMaxWidth()) {
                        Text(text = "$questionNo : ")
                        WrittenQuestionStyle02(
                            modifier = Modifier.weight(1f),
                            question = it.question,
                            onAnswerButtonClick =onAnswerButtonClick
                        )
                        Text(text = "${it.marks}")
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
        Row(modifier = Modifier) {
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = onAnswerButtonClick) {
                Text(text = "Solution")
            }
        }

    }

}