package com.nikhilhere.graphs.ui

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.nikhilhere.graphs.data.DataPoints

@Composable
fun InteractiveGraphScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var yAxisStepValue by remember { mutableStateOf(200f) }
        var xAxisStepValue by remember { mutableStateOf(200f) }
        var smoothness by remember { mutableStateOf(2f) }
        var scaleX by remember { mutableStateOf(1f) }
        var scaleY by remember { mutableStateOf(1f) }
        var showHorizontalGrid by remember { mutableStateOf(false) }
        var showVerticalGrid by remember { mutableStateOf(false) }
        var showControlPoints by remember { mutableStateOf(false) }

        Graph(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(horizontal = 40.dp, vertical = 16.dp)
                .clipToBounds(),
            points = DataPoints.points,
            yAxisStepValue = yAxisStepValue,
            xAxisStepValue = xAxisStepValue,
            curveSmoothNess = smoothness,
            scaleX = scaleX,
            scaleY = scaleY,
            showVerticalGrid = showVerticalGrid,
            showHorizontalGrid = showHorizontalGrid,
            showControlPoints = showControlPoints
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .horizontalScroll(
                        rememberScrollState()
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Checkbox(
                    checked = showHorizontalGrid,
                    onCheckedChange = { showHorizontalGrid = it })
                Text(text = "H-Grid", style = MaterialTheme.typography.caption)

                Checkbox(
                    checked = showVerticalGrid,
                    onCheckedChange = { showVerticalGrid = it })
                Text(text = "V-Grid", style = MaterialTheme.typography.caption)

                Checkbox(
                    checked = showControlPoints,
                    onCheckedChange = { showControlPoints = it })
                Text(text = "Control Points", style = MaterialTheme.typography.caption)
            }

            Divider()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "X-Axis Step Value = ($xAxisStepValue)",
                style = MaterialTheme.typography.caption
            )
            Slider(
                value = xAxisStepValue,
                valueRange = 100f..200f,
                steps = 3,
                onValueChange = {
                    xAxisStepValue = it
                })

            Divider()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Y-Axis Step Value = ($yAxisStepValue)",
                style = MaterialTheme.typography.caption
            )
            Slider(
                value = yAxisStepValue,
                valueRange = 100f..200f,
                steps = 3,
                onValueChange = {
                    yAxisStepValue = it
                })

            Divider()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Curve Smoothness = ($smoothness)",
                style = MaterialTheme.typography.caption
            )
            Spacer(modifier = Modifier.height(16.dp))
            Slider(
                value = smoothness,
                valueRange = 1f..4f,
                steps = 3,
                onValueChange = {
                    smoothness = it
                })


            Divider()
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "ScaleX = ($scaleX)", style = MaterialTheme.typography.caption)
            Slider(
                value = scaleX,
                valueRange = 1f..4f,
                steps = 3,
                onValueChange = {
                    scaleX = it
                })

            Divider()
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "ScaleY ($scaleY)", style = MaterialTheme.typography.caption)
            Slider(
                value = scaleY,
                valueRange = 1f..4f,
                steps = 3,
                onValueChange = {
                    scaleY = it
                }
            )


        }
    }
}