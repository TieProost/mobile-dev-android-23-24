package com.tieproost.fitnessapp.ui.exercise.overview

import com.tieproost.fitnessapp.model.Exercise

data class ExerciseOverviewState(
    val isDialogVisible: Boolean = false,
)

data class ExerciseListState(
    val exercises: List<Exercise> = listOf(),
)

sealed interface ExerciseOverviewApiState {
    object Success : ExerciseOverviewApiState

    object Error : ExerciseOverviewApiState

    object Loading : ExerciseOverviewApiState
}
