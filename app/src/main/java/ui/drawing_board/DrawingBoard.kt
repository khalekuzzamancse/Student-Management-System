package ui.drawing_board

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.input.pointer.pointerInteropFilter

import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun DrawingBoardPreview() {
    DrawingBoard()
}

@Composable
fun DrawingBoard() {
    var path by remember { mutableStateOf(Path()) }
    var paths by remember { mutableStateOf(mutableListOf<Path>()) }


    val updatePathOnDragStart: (Offset) -> Unit = { offset ->
        path = Path().apply {
            addPath(path)
            moveTo(offset.x, offset.y)
        }
    }
    val updatePathOnDragging: (Offset) -> Unit = { offset ->
        path = Path().apply {
            addPath(path)
            lineTo(offset.x, offset.y)
        }
    }
    val undo: () -> Unit = {
        if (paths.isNotEmpty()) {
            paths.removeAt(paths.size - 1)
            path = if (paths.isNotEmpty()) paths.last() else Path()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        updatePathOnDragStart(offset)
                    },
                    onDrag = { change, offset ->
                        updatePathOnDragging(change.position)
                    },
                    onDragEnd = {
                        paths.add(path)
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
            Button(onClick =undo) {
                Text(text = "Undo")
            }
        }

    }
}



