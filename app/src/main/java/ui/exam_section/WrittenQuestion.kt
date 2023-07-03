package ui.exam_section

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import ui.drawing_board.DrawingBoard02

@Preview
@Composable
private fun Preview() {
    WrittenQuestionStyle02()
}

@Composable
fun WrittenQuestionStyle02() {
    var giveAnswer by remember {
        mutableStateOf(false)
    }

    val showQuestion: @Composable () -> Unit = {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "What is your country name ?")
            Button(onClick = {
                giveAnswer = true
            }) {
                Text(text = "Give Answer")
            }
        }
    }
    Box(modifier = Modifier.fillMaxWidth()) {
        if (giveAnswer) {
            Column(modifier = Modifier.fillMaxSize()) {
                Button(onClick = {
                    giveAnswer = false
                }) {
                    Text(text = "Submit")
                }
                DrawingBoard02()
            }

        } else {
            showQuestion()
        }
    }

}