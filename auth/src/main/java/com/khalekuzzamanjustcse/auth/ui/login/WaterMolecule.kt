package com.khalekuzzamanjustcse.auth.ui.login


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun WaterMoleculeAnimation(
    modifier: Modifier = Modifier,
    initialValue: Float = 100f,
    targetValue: Float = 500f,
    durationX:Int=7000,
    durationY: Int=6000
    ) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val xValue by infiniteTransition.animateFloat(
        initialValue = initialValue,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(durationX, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    val yValue by infiniteTransition.animateFloat(
        initialValue = initialValue,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(durationY, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    Column(modifier = modifier) {
        WaterMolecule(
            modifier = Modifier
                .padding(8.dp)
                .wrapContentSize()
                .size(size = 50.dp)
                .graphicsLayer {
                    translationX = xValue
                    translationY = yValue
                }
        )

    }

}

@Composable
fun WaterMolecule(modifier: Modifier = Modifier) {
    val textMeasurer = rememberTextMeasurer()
    Canvas(
        modifier = modifier
    ) {
        val diameter = 2 * (size.width / 5)
        val radius = diameter / 2
        val firstCenter = Offset(size.width / 2, radius)
        val secondCenter = Offset(radius, (size.height - radius))
        val thirdCenter = Offset(size.width - radius, (size.height - radius))

        //drawing the lines
        val strokeWidth = radius / 10
        drawLine(
            start = firstCenter,
            end = secondCenter,
            color = Color.Black,
            strokeWidth = strokeWidth,
        )
        drawLine(
            start = firstCenter,
            end = thirdCenter,
            color = Color.Black,
            strokeWidth = strokeWidth,
        )
        //drawing molecules
        molecule(
            center = firstCenter,
            radius = radius,
            textMeasurer = textMeasurer,
            text = "O",
            color = Color.Red,
        )
        molecule(
            center = secondCenter,
            radius = radius,
            textMeasurer = textMeasurer,
            text = "H",
            color = Color.Blue,
        )
        molecule(
            center = thirdCenter,
            radius = radius,
            textMeasurer = textMeasurer,
            text = "H",
            color = Color.Blue,
        )


    }
}


fun DrawScope.molecule(
    center: Offset,
    text: String,
    radius: Float,
    textMeasurer: TextMeasurer,
    color: Color
) {
    drawCircle(
        color = color,
        radius = radius,
        center = center
    )
    val fontSize = radius / 2
    val textSize = textMeasurer
        .measure(
            text = text, style = TextStyle(
                fontSize = fontSize.sp,
                color = Color.White,
            )
        ).size
    drawText(
        textMeasurer,
        text = text,
        topLeft = Offset(
            x = center.x - textSize.width / 2,
            y = center.y - textSize.height / 2
        ),
        style = TextStyle(
            color = Color.White,
            fontSize = fontSize.sp
        )

    )
}

