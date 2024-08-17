package com.tieproost.fitnessapp.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tieproost.fitnessapp.ui.common.ErrorScreen
import com.tieproost.fitnessapp.ui.common.LoadingScreen
import com.tieproost.fitnessapp.ui.dashboard.components.CaloriesCard
import com.tieproost.fitnessapp.ui.dashboard.components.SettingsCard
import com.tieproost.fitnessapp.ui.navigation.NavigationDestinations

@Composable
fun DashboardScreen() {
    val dashboardViewModel: DashboardViewModel = viewModel(factory = DashboardViewModel.Factory)
    val uiState by dashboardViewModel.uiState.collectAsState()
    val apiState = dashboardViewModel.apiState

    Box(
        modifier =
            Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .testTag(stringResource(NavigationDestinations.Dashboard.textId)),
    ) {
        when (apiState) {
            is DashboardApiState.Loading -> LoadingScreen()
            is DashboardApiState.Error -> ErrorScreen()
            is DashboardApiState.Success ->
                DashBoardOverview(
                    uiState = uiState,
                )
        }
    }
}

@Composable
fun DashBoardOverview(uiState: DashboardState) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        CaloriesCard(
            goal = uiState.settings.calorieGoal,
            foodCalories = uiState.foodCalories,
            exerciseCalories = uiState.exerciseCalories,
        )

        SettingsCard(
            settings = uiState.settings,
        )
    }
}
