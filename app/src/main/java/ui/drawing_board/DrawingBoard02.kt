package ui.drawing_board

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun DrawingBoardPreview() {
    DrawingBoard02()
}

@Composable
fun DrawingBoard02(modifier: Modifier=Modifier) {
    var path by remember { mutableStateOf(Path()) }
    var paths by remember { mutableStateOf(mutableListOf<Path>()) }
    var previousPosition: Offset = Offset.Zero
    var currentPosition: Offset


    val updatePathOnDragStart: (Offset) -> Unit = { offset ->
        path = Path().apply {
            addPath(path)
            moveTo(offset.x, offset.y)
        }
        currentPosition = offset
        previousPosition = currentPosition
    }
    val updatePathOnDragging: (Offset) -> Unit = { offset ->
        currentPosition = offset
        path = Path().apply {
            addPath(path)
            quadraticBezierTo(
                previousPosition.x,
                previousPosition.y,
                (previousPosition.x + currentPosition.x) / 2,
                (previousPosition.y + currentPosition.y) / 2

            )
            previousPosition = currentPosition
        }
    }
    val undo: () -> Unit = {
        if (paths.isNotEmpty()) {
            paths.removeAt(paths.size - 1)
            path = if (paths.isNotEmpty()) paths.last() else Path()
        }
    }
    val cleanBoard: () -> Unit = {
        paths = mutableListOf()
        previousPosition = Offset.Zero
        currentPosition = Offset.Zero
        path = Path()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        updatePathOnDragStart(offset)
                    },
                    onDrag = { change, _ ->
                        updatePathOnDragging(change.position)
                    },
                    onDragEnd = {
                        paths.add(path)
                        currentPosition = Offset.Unspecified
                        previousPosition = currentPosition
                    }
                )
            }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Canvas(modifier = Modifier.weight(1f)) {
                drawPath(
                    path = path,
                    color = Color.Black,
                    style = Stroke(width = 4.dp.toPx())
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = undo) {
                    Text(text = "Undo")
                }
                Button(onClick = cleanBoard) {
                    Text(text = "Clear")
                }

            }

        }

    }
}
