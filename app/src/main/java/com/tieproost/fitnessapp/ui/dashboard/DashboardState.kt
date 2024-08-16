package com.tieproost.fitnessapp.ui.dashboard

import com.tieproost.fitnessapp.model.Settings

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
