package com.tieproost.fitnessapp.ui.exercise.mutate

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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel class responsible for managing the state and actions of the exercise mutate dialog.
 *
 * @property exerciseRepository The repository for saving exercises.
 */
class ExerciseMutateViewModel(
    private val exerciseRepository: ExerciseRepository,
) : ViewModel() {
    var apiState: ExerciseMutateApiState by mutableStateOf(ExerciseMutateApiState.Success)
        private set

    // cold flow
    private val _uiState = MutableStateFlow(ExerciseMutateState())
    val uiState: StateFlow<ExerciseMutateState> = _uiState.asStateFlow()

    fun updateQuery(query: String) {
        apiState = ExerciseMutateApiState.Success
        _uiState.update {
            it.copy(
                query = query,
            )
        }
    }

    fun search() {
        viewModelScope.launch {
            apiState = ExerciseMutateApiState.Loading
            exerciseRepository
                .searchWorkout(uiState.value.query)
                .catch {
                    apiState = ExerciseMutateApiState.Error
                }.collect {
                    if (it.exercises.isEmpty()) {
                        apiState = ExerciseMutateApiState.Error
                    } else {
                        _uiState.update { currentState ->
                            currentState.copy(
                                results = currentState.results + it.exercises,
                            )
                        }
                        apiState = ExerciseMutateApiState.Success
                    }
                }
        }
    }

    fun addWorkout() {
        viewModelScope.launch {
            _uiState.value.results.forEach {
                exerciseRepository.insertExercise(it)
            }
        }

        // reset the input fields
        _uiState.update { currentState ->
            currentState.copy(
                query = "",
                results = listOf(),
            )
        }
    }

    fun clearResults() {
        _uiState.update {
            it.copy(
                results = listOf(),
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val application = (
                        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as
                            FitnessApplication
                    )
                    val exerciseRepository = application.container.exerciseRepository
                    ExerciseMutateViewModel(exerciseRepository = exerciseRepository)
                }
            }
    }
}
