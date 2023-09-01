package com.khalekuzzamanjustcse.auth.ui.login

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


@Composable
fun FormulaAnimation(modifier: Modifier = Modifier) {
    val textMeasurer = rememberTextMeasurer()
    val textParts = listOf("50", "=", "20", "+", "30")
    val initialPositions = remember { mutableStateListOf<Offset>() }
    val positions = remember { mutableStateListOf<Offset>() }
    val canvasSize = remember { mutableStateOf(Size.Zero) }
    var shouldUpdate by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(6000)
            shouldUpdate = false
            positions.clear()
            positions.addAll(initialPositions)
            delay(3000)
            shouldUpdate = true
        }
    }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            if (shouldUpdate) {
                for (i in positions.indices) {
                    positions[i] = Offset(
                        x = Random.nextInt(0, canvasSize.value.width.toInt()).toFloat(),
                        y = Random.nextInt(0, canvasSize.value.height.toInt()).toFloat()
                    )
                }
            }
        }
    }
    Canvas(
        modifier = modifier.size(100.dp).onGloballyPositioned { coordinates ->
            canvasSize.value = coordinates.size.toSize()
        }
    ) {
        var currentX = 0f
        val currentY = size.height / 2
        for ((index, text) in textParts.withIndex()) {
            val textSize = textMeasurer.measure(text).size
            if (initialPositions.size <= index) {
                initialPositions.add(
                    Offset(
                        x = currentX,
                        y = currentY - textSize.height / 2
                    )
                )
                positions.add(initialPositions.last())
            }
            drawText(
                textMeasurer,
                text = text,
                topLeft = positions[index],
                style = TextStyle(
                    fontSize = 15.sp
                )
            )
            currentX += textSize.width + 5
        }
    }
}

