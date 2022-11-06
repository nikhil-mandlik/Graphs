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
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nikhilhere.graphs.ui.Graph
import com.nikhilhere.graphs.ui.InteractiveGraphScreen
import com.nikhilhere.graphs.ui.MainScreen
import com.nikhilhere.graphs.ui.theme.GraphsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "interactive") {
                composable("main") {
                    MainScreen(
                        navigateToInteractiveGraph = {
                            navController.navigate("interactive")
                        }
                    )
                }

                composable("interactive") {
                    InteractiveGraphScreen()
                }
            }

        }
    }
}


