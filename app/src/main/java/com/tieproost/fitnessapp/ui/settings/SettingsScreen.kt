package com.tieproost.fitnessapp.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tieproost.fitnessapp.model.Settings
import com.tieproost.fitnessapp.ui.common.ErrorScreen
import com.tieproost.fitnessapp.ui.common.LoadingScreen
import com.tieproost.fitnessapp.ui.navigation.NavigationDestinations
import com.tieproost.fitnessapp.ui.settings.dialog.CalorieGoalDialogContent
import com.tieproost.fitnessapp.ui.settings.dialog.HeightDialogContent
import com.tieproost.fitnessapp.ui.settings.dialog.SexDialogContent
import com.tieproost.fitnessapp.ui.settings.dialog.WeightDialogContent
import com.tieproost.fitnessapp.ui.util.booleanToSexString
import com.tieproost.fitnessapp.ui.util.nullableToString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    val settingsViewModel: SettingsViewModel = viewModel(factory = SettingsViewModel.Factory)
    val uiState by settingsViewModel.uiState.collectAsState()
    val uiObjectState by settingsViewModel.uiObjectState.collectAsState()
    val apiState = settingsViewModel.apiState

    val showBirthdayDialog = {
        settingsViewModel.showDialog {
            DatePicker(
                state = uiState.datePickerState,
            )
        }
    }

    val showSexDialog = {
        settingsViewModel.showDialog {
            SexDialogContent(
                value = uiState.settingsForm.sex,
                onValueChange = settingsViewModel::onSexChange,
            )
        }
    }

    val showHeightDialog = {
        settingsViewModel.showDialog {
            HeightDialogContent(
                value = nullableToString(uiState.settingsForm.height, ""),
                onValueChange = settingsViewModel::onHeightChange,
            )
        }
    }

    val showWeightDialog = {
        settingsViewModel.showDialog {
            WeightDialogContent(
                value = nullableToString(uiState.settingsForm.weight, ""),
                onValueChange = settingsViewModel::onWeightChange,
            )
        }
    }

    val showCalorieGoalDialog = {
        settingsViewModel.showDialog {
            CalorieGoalDialogContent(
                value = nullableToString(uiState.settingsForm.calorieGoal, ""),
                onValueChange = settingsViewModel::onCalorieGoalChange,
            )
        }
    }

    Box(
        modifier =
            Modifier
                .fillMaxHeight()
                .testTag(stringResource(NavigationDestinations.Settings.textId)),
    ) {
        when (apiState) {
            is SettingsApiState.Loading -> LoadingScreen()
            is SettingsApiState.Error -> ErrorScreen()
            is SettingsApiState.Success ->
                SettingsOverview(
                    settings = uiObjectState.settings,
                    showBirthdayDialog = showBirthdayDialog,
                    showSexDialog = showSexDialog,
                    showWeightDialog = showWeightDialog,
                    showHeightDialog = showHeightDialog,
                    showCalorieGoalDialog = showCalorieGoalDialog,
                )
        }

        if (uiState.isDialogVisible) {
            SettingsDialog(
                dialogContent = uiState.dialogContent,
                hideDialog = settingsViewModel::hideDialog,
                saveSettings = settingsViewModel::saveSettings,
            )
        }
    }
}

@Composable
fun SettingsOverview(
    settings: Settings,
    showBirthdayDialog: () -> Unit,
    showSexDialog: () -> Unit,
    showHeightDialog: () -> Unit,
    showWeightDialog: () -> Unit,
    showCalorieGoalDialog: () -> Unit,
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = "Settings: ",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp),
        )

        SettingListItem(
            onClick = showBirthdayDialog,
            title = "Birthday",
            value = "${settings.birthDate ?: " - "}",
        )

        SettingListItem(
            onClick = showSexDialog,
            title = "Sex",
            value = booleanToSexString(settings.sex),
        )

        SettingListItem(
            onClick = showHeightDialog,
            title = "Height",
            value = "${settings.height ?: " - "} cm",
        )

        SettingListItem(
            onClick = showWeightDialog,
            title = "Weight",
            value = "${settings.weight ?: " - "} kg",
        )
        SettingListItem(
            onClick = showCalorieGoalDialog,
            title = "Daily Goal",
            value = "${settings.calorieGoal } kcal",
        )

        Text(
            text = "About: ",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp),
        )

        ListItem(
            headlineContent = {
                Text(text = "Build with Nutrionix API © All rights reserved.")
            },
        )
    }
}

@Composable
fun SettingListItem(
    onClick: () -> Unit,
    title: String,
    value: String,
) {
    ListItem(
        modifier =
            Modifier
                .clickable { onClick() }
                .testTag(stringResource(NavigationDestinations.Settings.textId) + "AddButton"),
        leadingContent = {
            Text(text = title)
        },
        headlineContent = {
            Text(
                text = value,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.secondary,
            )
        },
    )
    HorizontalDivider()
}
