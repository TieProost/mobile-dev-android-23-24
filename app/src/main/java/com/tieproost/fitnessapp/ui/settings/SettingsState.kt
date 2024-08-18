package com.tieproost.fitnessapp.ui.settings

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.tieproost.fitnessapp.model.Settings
import java.util.Locale

/**
 * Represents the state of the settings screen.
 *
 * @property isDialogVisible Whether the dialog for editing settings is visible.
 * @property dialogContent Content to display in dialog.
 * @property settingsForm [Settings] that are being edited.
 * @property datePickerState [DatePickerState] for DatePicker dialog.
 */
data class SettingsState
    @OptIn(ExperimentalMaterial3Api::class)
    constructor(
        val isDialogVisible: Boolean = false,
        val dialogContent: @Composable () -> Unit = { },
        val settingsForm: Settings = Settings(),
        val datePickerState: DatePickerState = DatePickerState(Locale.FRENCH),
    )

data class SettingsObjectState(
    val settings: Settings = Settings(),
)

sealed interface SettingsApiState {
    object Success : SettingsApiState

    object Error : SettingsApiState

    object Loading : SettingsApiState
}
