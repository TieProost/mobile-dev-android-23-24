package com.tieproost.fitnessapp.ui.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.tieproost.fitnessapp.FitnessApplication
import com.tieproost.fitnessapp.data.repository.ExerciseRepository
import com.tieproost.fitnessapp.data.repository.MealsRepository
import com.tieproost.fitnessapp.data.repository.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel class responsible for managing the state and actions of the dashboard.
 *
 * @property mealsRepository The repository for fetching total calories of food.
 * @property exerciseRepository The repository for fetching total calories of exercises.
 * @property settingsRepository The repository for fetching settings.
 */
class DashboardViewModel(
    private val mealsRepository: MealsRepository,
    private val exerciseRepository: ExerciseRepository,
    private val settingsRepository: SettingsRepository,
) : ViewModel() {
    var apiState: DashboardApiState by mutableStateOf(DashboardApiState.Loading)
        private set

    private val settings = settingsRepository.getSettings()
    private val caloriesEaten = mealsRepository.getTotalCalories()
    private val caloriesBurned = exerciseRepository.getTotalCalories()

    val uiState: StateFlow<DashboardState> =
        combine(
            settings,
            caloriesBurned,
            caloriesEaten,
        ) { settings, caloriesBurned, caloriesEaten ->

            apiState = DashboardApiState.Success

            DashboardState(
                settings = settings,
                foodCalories = caloriesEaten,
                exerciseCalories = caloriesBurned,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = DashboardState(),
        )

    // object to tell the android framework how to handle the parameter of the viewModel
    companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val application =
                        (
                            this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as
                                FitnessApplication
                        )
                    DashboardViewModel(
                        mealsRepository = application.container.mealsRepository,
                        exerciseRepository = application.container.exerciseRepository,
                        settingsRepository = application.container.settingsRepository,
                    )
                }
            }
    }
}
