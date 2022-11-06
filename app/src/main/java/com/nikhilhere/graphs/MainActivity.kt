package com.nikhilhere.graphs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikhilhere.graphs.ui.Graph
import com.nikhilhere.graphs.ui.theme.GraphsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                var defaultYAxisStepValue by remember {
                    mutableStateOf(100)
                }

                var defaultXaxisScaleRation by remember {
                    mutableStateOf(1f)
                }

                Graph(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .padding(horizontal = 40.dp, vertical = 40.dp),
                    points = listOf(
                        Pair(1, 300),
                        Pair(2, 80),
                        Pair(3, 500),
                        Pair(4, 600)
                    ),
                    yAxisStepValue = defaultYAxisStepValue,
                    xAxisStepValueRatio = defaultXaxisScaleRation
                )

                Spacer(modifier = Modifier.height(56.dp))

                Slider(
                    modifier = Modifier.padding(40.dp),
                    value = defaultYAxisStepValue.toFloat(),
                    valueRange = 60f..200f,
                    steps = 5,
                    onValueChange = {
                        defaultYAxisStepValue = it.toInt()
                    }
                )

                Slider(
                    modifier = Modifier.padding(40.dp),
                    value = defaultXaxisScaleRation,
                    valueRange = 0.5f..2f,
                    steps = 5,
                    onValueChange = {
                        defaultXaxisScaleRation = it
                    }
                )

            }
        }
    }
}