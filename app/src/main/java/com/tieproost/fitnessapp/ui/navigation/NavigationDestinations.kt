package com.tieproost.fitnessapp.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.tieproost.fitnessapp.R
import com.tieproost.fitnessapp.ui.dashboard.DashboardScreen
import com.tieproost.fitnessapp.ui.exercise.ExerciseScreen
import com.tieproost.fitnessapp.ui.meals.MealsScreen
import com.tieproost.fitnessapp.ui.settings.SettingsScreen

enum class NavigationDestinations(
    @StringRes val textId: Int,
    val icon: ImageVector,
    val content: @Composable () -> Unit,
) {
    Dashboard(
        textId = R.string.dashboard,
        icon = Icons.Filled.Dashboard,
        content = { DashboardScreen() },
    ),
    Meals(
        textId = R.string.meals,
        icon = Icons.Filled.Restaurant,
        content = { MealsScreen() },
    ),
    Exercise(
        textId = R.string.exercise,
        icon = Icons.AutoMirrored.Filled.DirectionsRun,
        content = { ExerciseScreen() },
    ),
    Settings(
        textId = R.string.settings,
        icon = Icons.Filled.Settings,
        content = { SettingsScreen() },
    ),
}
