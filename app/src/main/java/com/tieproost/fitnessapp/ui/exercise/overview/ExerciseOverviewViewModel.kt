package com.tieproost.fitnessapp.ui.exercise.overview

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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.io.IOException

class ExerciseOverviewViewModel(
    private val exerciseRepository: ExerciseRepository,
) : ViewModel() {
    var apiState: ExerciseOverviewApiState by mutableStateOf(ExerciseOverviewApiState.Loading)
        private set

    // uiState is a cold flow. Changes don't come in from above unless a refresh is called
    private val _uiState = MutableStateFlow(ExerciseOverviewState())
    val uiState: StateFlow<ExerciseOverviewState> = _uiState.asStateFlow()

    // uiListState is a hot flow (.stateIn makes it so)
    lateinit var uiListState: StateFlow<ExerciseListState>

    init {
        getExercises()
    }

    private fun getExercises() {
        try {
            uiListState =
                exerciseRepository
                    .getExercises()
                    .map { ExerciseListState(it) }
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5_000L),
                        initialValue = ExerciseListState(),
                    )

            apiState = ExerciseOverviewApiState.Success
        } catch (e: IOException) {
            apiState = ExerciseOverviewApiState.Error
        }
    }

    fun hideDialog() {
        _uiState.update {
            it.copy(isDialogVisible = false)
        }
    }

    fun showDialog() {
        _uiState.update {
            it.copy(isDialogVisible = true)
        }
    }

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
                    val exerciseRepository = application.container.exerciseRepository
                    ExerciseOverviewViewModel(exerciseRepository = exerciseRepository)
                }
            }
    }
}
