package com.tieproost.fitnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.tieproost.fitnessapp.ui.FitnessApp
import com.tieproost.fitnessapp.ui.theme.FitnessAppTheme
import com.tieproost.fitnessapp.ui.util.NavigationType

/**
 * Main activity for the application.
 * Extends [ComponentActivity] and is responsible for initializing the user interface.
 */
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    when (calculateWindowSizeClass(activity = this).widthSizeClass) {
                        WindowWidthSizeClass.Compact -> {
                            FitnessApp(navigationType = NavigationType.BOTTOM_NAVIGATION)
                        }
                        else -> {
                            FitnessApp(navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER)
                        }
                    }
                }
            }
        }
    }
}
