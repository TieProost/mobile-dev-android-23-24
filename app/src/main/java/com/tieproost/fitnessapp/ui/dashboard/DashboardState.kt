package com.tieproost.fitnessapp.ui.dashboard

import com.tieproost.fitnessapp.model.Settings

/**
 * Represents the state of the dashboard screen.
 *
 * @property foodCalories The daily total eaten food calories.
 * @property exerciseCalories The daily total burned exercise calories.
 * @property settings The settings for the user.
 */
data class DashboardState(
    val foodCalories: Double = 0.0,
    val exerciseCalories: Double = 0.0,
    val settings: Settings = Settings(),
)

sealed interface DashboardApiState {
    object Success : DashboardApiState

    object Error : DashboardApiState

    object Loading : DashboardApiState
}
