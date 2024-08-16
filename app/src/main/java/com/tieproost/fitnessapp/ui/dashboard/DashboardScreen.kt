package com.tieproost.fitnessapp.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tieproost.fitnessapp.model.Settings
import com.tieproost.fitnessapp.ui.dashboard.components.CaloriesCard
import com.tieproost.fitnessapp.ui.navigation.NavigationDestinations
import com.tieproost.fitnessapp.ui.util.booleanToSexString
import java.time.LocalDate
import java.time.Period

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
            is DashboardApiState.Loading -> Text("Loading...")
            is DashboardApiState.Error -> Text("Couldn't load...")
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
        modifier =
            Modifier
                .padding(16.dp),
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

        Card {
            Text(text = "Intake chart")
        }
    }
}

@Composable
fun SettingsCard(settings: Settings) {
    val age =
        if (settings.birthDate == null) {
            " - "
        } else {
            Period.between(settings.birthDate, LocalDate.now()).years.toString()
        }

    Card(
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onSecondary,
            ),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
        ) {
            Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                SettingItem(
                    icon = Icons.Filled.CalendarMonth,
                    title = "Age",
                    value = age,
                )
                SettingItem(
                    icon = Icons.Filled.Person,
                    title = "Sex",
                    value = booleanToSexString(settings.sex),
                )
            }
            Column {
                SettingItem(
                    icon = Icons.Filled.Scale,
                    title = "Weight",
                    value = settings.weight.toString(),
                )
                SettingItem(
                    icon = Icons.Filled.Height,
                    title = "Height",
                    value = settings.height.toString(),
                )
            }
        }
    }
}

@Composable
private fun SettingItem(
    title: String,
    value: String,
    icon: ImageVector,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.padding(12.dp),
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        Column {
            Text(text = title)
            Text(text = value)
        }
    }
}
