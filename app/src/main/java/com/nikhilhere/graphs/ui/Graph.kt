package com.nikhilhere.graphs.ui

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max


private const val TAG = "Graph"

@Composable
fun Graph(
    modifier: Modifier,
    points: List<Pair<Int, Int>>,
    yAxisStepValue: Float,
    xAxisStepValue: Float,
    curveSmoothNess: Float = 2f,
    scaleX: Float,
    scaleY: Float
) {

    Log.i(TAG, "Graph: points $points")
    Log.i(TAG, "Graph: yAxisStepValue $yAxisStepValue")
    Log.i(TAG, "Graph: xAxisStepValue $xAxisStepValue")
    Log.i(TAG, "Graph: curveSmoothNess $curveSmoothNess")

    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {

            Log.i(TAG, "Graph: size $size")

            // define size for the x-axis and y-axis labels
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

            // draw closing axis, top_left -> top_right, top_right -> bottom_right
            drawLine(
                start = Offset(0f + labelSize.width, 0f),
                end = Offset(size.width, 0f),
                color = Color.Black
            )
            drawLine(
                start = Offset(size.width, 0f),
                end = Offset(size.width, size.height - labelSize.height),
                color = Color.Black
            )

            // draw x-axis labels
            (0 until (size.width / xAxisStepValue).toInt()).map {
                val labelText = "${(xAxisStepValue * (it + 1)).toInt()}"
                val x = (xAxisStepValue * (it + 1) + labelSize.width) * scaleX
                val y = size.height
                drawContext.canvas.nativeCanvas.drawText(
                    labelText,
                    x,
                    y,
                    android.graphics.Paint().apply {
                        textSize = 10.dp.toPx()
                        color = android.graphics.Color.BLACK
                    }
                )

                // draw vertical grid
                drawLine(
                    color = Color.Black,
                    start = Offset(x, size.height - labelSize.height),
                    end = Offset(x, 0f),
                )
            }

            // draw y-axis labels
            (0 until (size.height / yAxisStepValue).toInt()).map {
                val x = 0f
                val y = (size.height - labelSize.height - (yAxisStepValue * (it + 1))) * scaleY
                drawContext.canvas.nativeCanvas.drawText(
                    "${(yAxisStepValue * (it + 1)).toInt()}",
                    x,
                    y,
                    android.graphics.Paint().apply {
                        textSize = 10.dp.toPx()
                        color = android.graphics.Color.BLACK
                    }
                )

                // draw horizontal grid
                drawLine(
                    color = Color.Black,
                    start = Offset(x + labelSize.width, y),
                    end = Offset(size.width, y),
                )
            }

            // draw co-ordinates
            val coordinates = mutableListOf<Offset>()
            points.forEach { point ->
                val x = point.first.toFloat() * scaleX
                val y = (size.height - point.second - labelSize.height) * scaleY
                coordinates.add(Offset(x, y))
                drawCircle(
                    color = Color.Black,
                    radius = 10f,
                    center = Offset(x, y)
                )
            }

            // draw paths and control points
            (0 until coordinates.size - 1).map { index ->
                val startPoint = coordinates[index]
                val endPoint = coordinates[index + 1]
                val c1 = Offset(
                    x = startPoint.x + xAxisStepValue / curveSmoothNess,
                    y = startPoint.y
                )
                val c2 = Offset(
                    x = endPoint.x - xAxisStepValue / curveSmoothNess,
                    y = endPoint.y
                )

                drawCircle(
                    color = Color.Red,
                    radius = 6f,
                    center = Offset(c1.x, c1.y)
                )

                drawCircle(
                    color = Color.Red,
                    radius = 6f,
                    center = Offset(c2.x, c2.y)
                )

                val path = Path().apply {
                    moveTo(startPoint.x, startPoint.y)
                    cubicTo(c1.x, c1.y, c2.x, c2.y, endPoint.x, endPoint.y)
                }

                drawPath(
                    path = path,
                    color = Color.Black,
                    style = Stroke(width = 2f)
                )
            }

        }
    }
}