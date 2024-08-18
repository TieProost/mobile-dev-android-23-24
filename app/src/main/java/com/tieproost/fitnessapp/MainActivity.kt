package com.tieproost.fitnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.tieproost.fitnessapp.ui.FitnessApp
import com.tieproost.fitnessapp.ui.theme.FitnessAppTheme

/**
 * Main activity for the application.
 * Extends [ComponentActivity] and is responsible for initializing the user interface.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    FitnessApp()
                }
            }
        }
    }
}
