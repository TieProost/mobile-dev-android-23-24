package com.tieproost.fitnessapp.ui.settings

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.tieproost.fitnessapp.FitnessApplication
import com.tieproost.fitnessapp.data.repository.SettingsRepository
import com.tieproost.fitnessapp.ui.util.nullableToString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.Locale

/**
 * ViewModel class responsible for managing the state and actions of settings screen.
 *
 * @property settingsRepository The repository for fetching settings.
 */
@OptIn(ExperimentalMaterial3Api::class)
class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {
    var apiState: SettingsApiState by mutableStateOf(SettingsApiState.Loading)
        private set

    // cold flow
    private val _uiState = MutableStateFlow(SettingsState())
    val uiState: StateFlow<SettingsState> = _uiState.asStateFlow()

    // hot flow
    lateinit var uiObjectState: StateFlow<SettingsObjectState>

    init {
        getSettings()
    }

    private fun getSettings() {
        try {
            val settingsFlow = settingsRepository.getSettings()

            uiObjectState =
                settingsFlow
                    .map { SettingsObjectState(it) }
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5_000L),
                        initialValue = SettingsObjectState(),
                    )

            viewModelScope.launch {
                settingsFlow.collect { settings ->
                    _uiState.update {
                        it.copy(
                            settingsForm = settings,
                        )
                    }

                    updateDatePickerState(settings.birthDate)
                }
            }

            apiState = SettingsApiState.Success
        } catch (e: IOException) {
            apiState = SettingsApiState.Error
        }
    }

    fun hideDialog() {
        _uiState.update {
            it.copy(isDialogVisible = false)
        }
        resetFormFields()
    }

    fun showDialog(dialogContent: @Composable () -> Unit) {
        _uiState.update {
            it.copy(
                isDialogVisible = true,
                dialogContent = dialogContent,
            )
        }
    }

    fun onSexChange(inputString: String) {
        val sex =
            when (inputString) {
                "Male" -> true
                "Female" -> false
                else -> {
                    null
                }
            }

        updateSex(sex)
    }

    fun onHeightChange(inputString: String): String {
        val height =
            if (!inputString.all { it in '0'..'9' }) {
                _uiState.value.settingsForm.height
            } else {
                inputString.toIntOrNull()
            }

        updateHeight(height)

        return nullableToString(height, "")
    }

    fun onWeightChange(inputString: String): String {
        var outputString =
            if (inputString.count { it == '.' } > 1) {
                _uiState.value.settingsForm.weight
                    ?.toBigDecimal()
                    ?.toPlainString() ?: ""
            } else {
                inputString
            }

        val weight =
            if (!inputString.all { it in '0'..'9' || it in arrayOf('.') } || inputString.count { it == '.' } > 1) {
                outputString = outputString.replace("[^0-9.]".toRegex(), "")
                _uiState.value.settingsForm.weight
            } else {
                inputString.toDoubleOrNull()
            }

        updateWeight(weight)

        return outputString
    }

    fun onCalorieGoalChange(inputString: String): String {
        val calorieGoal =
            if (!inputString.all { it in '0'..'9' }) {
                _uiState.value.settingsForm.calorieGoal
            } else {
                inputString.toIntOrNull()
            }

        updateCalorieGoal(calorieGoal ?: 2000)

        return nullableToString(calorieGoal, "")
    }

    private fun updateCalorieGoal(calorieGoal: Int) {
        _uiState.update {
            it.copy(
                settingsForm = it.settingsForm.copy(calorieGoal = calorieGoal),
            )
        }
    }

    private fun updateHeight(height: Int?) {
        _uiState.update {
            it.copy(
                settingsForm = it.settingsForm.copy(height = height),
            )
        }
    }

    private fun updateWeight(weight: Double?) {
        _uiState.update {
            it.copy(
                settingsForm = it.settingsForm.copy(weight = weight),
            )
        }
    }

    private fun updateSex(sex: Boolean?) {
        _uiState.update {
            it.copy(
                settingsForm = it.settingsForm.copy(sex = sex),
            )
        }
    }

    private fun updateBirthDate() {
        if (_uiState.value.datePickerState.selectedDateMillis != null
        ) {
            val newDate =
                Instant
                    .ofEpochMilli(_uiState.value.datePickerState.selectedDateMillis!!)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()

            _uiState.update {
                it.copy(
                    settingsForm = it.settingsForm.copy(birthDate = newDate),
                )
            }
        }
    }

    private fun updateDatePickerState(currentDate: LocalDate?) {
        _uiState.update {
            it.copy(
                datePickerState =
                    DatePickerState(
                        Locale.FRENCH,
                        initialSelectedDateMillis =
                            currentDate
                                ?.atStartOfDay()
                                ?.toInstant(ZoneOffset.UTC)
                                ?.toEpochMilli(),
                    ),
            )
        }
    }

    fun saveSettings() {
        updateBirthDate()
        viewModelScope.launch {
            settingsRepository.save(_uiState.value.settingsForm)
        }
    }

    private fun resetFormFields() {
        _uiState.update {
            it.copy(
                settingsForm = uiObjectState.value.settings,
            )
        }
        updateDatePickerState(uiObjectState.value.settings.birthDate)
    }

    companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val application = (
                        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as
                            FitnessApplication
                    )
                    val settingsRepository = application.container.settingsRepository
                    SettingsViewModel(settingsRepository = settingsRepository)
                }
            }
    }
}
