package com.nikhilhere.graphs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nikhilhere.graphs.ui.Graph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .scrollable(rememberScrollState(), orientation = Orientation.Vertical),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                var yAxisStepValue by remember {
                    mutableStateOf(200f)
                }

                var xAxisStepValue by remember {
                    mutableStateOf(200f)
                }

                var curveSmoothNess by remember {
                    mutableStateOf(2f)
                }

                var scaleX by remember {
                    mutableStateOf(1f)
                }

                var scaleY by remember {
                    mutableStateOf(1f)
                }

                Graph(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f)
                        .padding(horizontal = 40.dp, vertical = 40.dp)
                        .clipToBounds(),
                    points = listOf(
                        Pair(100, 200),
                        Pair(180, 80),
                        Pair(240, 500),
                        Pair(360, 600),
                        Pair(440, 400),
                        Pair(520, 300),
                        Pair(620, 600),
                        Pair(700, 400),
                        Pair(780, 150),
                        Pair(860, 450),
                    ),
                    yAxisStepValue = yAxisStepValue,
                    xAxisStepValue = xAxisStepValue,
                    curveSmoothNess = curveSmoothNess,
                    scaleX = scaleX,
                    scaleY = scaleY
                )

                Spacer(modifier = Modifier.height(16.dp))

                Slider(
                    modifier = Modifier.padding(40.dp),
                    value = scaleY,
                    valueRange = 1f..4f,
                    steps = 3,
                    onValueChange = {
                        scaleY = it
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Slider(
                    modifier = Modifier.padding(40.dp),
                    value = scaleX,
                    valueRange = 1f..4f,
                    steps = 3,
                    onValueChange = {
                        scaleX = it
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Slider(
                    modifier = Modifier.padding(40.dp),
                    value = yAxisStepValue,
                    valueRange = 100f..200f,
                    steps = 3,
                    onValueChange = {
                        yAxisStepValue = it
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
                Slider(
                    modifier = Modifier.padding(40.dp),
                    value = xAxisStepValue,
                    valueRange = 100f..200f,
                    steps = 3,
                    onValueChange = {
                        xAxisStepValue = it
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
                Slider(
                    modifier = Modifier.padding(40.dp),
                    value = curveSmoothNess,
                    valueRange = 1f..4f,
                    steps = 3,
                    onValueChange = {
                        curveSmoothNess = it
                    }
                )

            }
        }
    }
}