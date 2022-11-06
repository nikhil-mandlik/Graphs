package com.nikhilhere.graphs.ui

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max


private const val TAG = "Graph"

@Composable
fun Graph(
    modifier: Modifier,
    points: List<Pair<Int, Int>>,
    yAxisStepValue : Int = 100,
    xAxisStepValueRatio : Float = 1f
) {

    Box(modifier = modifier.drawBehind {

    }) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val labelSize = Size(80f, 60f)

            // draw x-axis
            drawLine(
                start = Offset(0f + labelSize.width, size.height - labelSize.height),
                end = Offset(size.width, size.height - labelSize.height),
                color = Color.Black
            )

            // draw y-axis
            drawLine(
                start = Offset(0f + labelSize.width, size.height - labelSize.height),
                end = Offset(0f + labelSize.width, 0f),
                color = Color.Black
            )

            // drawing x-axis text
            val xAxisStepValue = (size.width / points.size) * xAxisStepValueRatio
            points.forEachIndexed { index, point ->
                drawContext.canvas.nativeCanvas.drawText(
                    "${point.first}",
                    xAxisStepValue * (index + 1),
                    size.height,
                    android.graphics.Paint().apply {
                        textSize = 10.dp.toPx()
                        color = android.graphics.Color.BLACK
                    }
                )
            }


            // drawing y-axis labels
            var yLabelIndex = 0
            do {
                val yLabelPosition = size.height - labelSize.height - (yAxisStepValue * (yLabelIndex  + 1))
                drawContext.canvas.nativeCanvas.drawText(
                    "${yAxisStepValue * (yLabelIndex + 1)}",
                    0f,
                    yLabelPosition,
                    android.graphics.Paint().apply {
                        textSize = 10.dp.toPx()
                        color = android.graphics.Color.BLACK
                    }
                )
                yLabelIndex++
            } while (yLabelPosition >= 0f)


            Log.i(TAG, "size: $size")
            points.forEachIndexed { i, point ->
                val x = xAxisStepValue * (i + 1)
                val y = size.height - labelSize.height - point.second
                Log.i(TAG, "Graph: point $point")
                Log.i(TAG, "Graph: x $x y $y")
                drawCircle(
                    color = Color.Black,
                    radius = 10f,
                    center = Offset(x, y.toFloat())
                )
            }
        }
    }
}