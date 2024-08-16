package com.tieproost.fitnessapp.ui.exercise.mutate

import com.tieproost.fitnessapp.network.model.ApiExercise

// the data class just holds the (immutable) values of the state
data class ExerciseMutateState(
    val query: String = "",
    val results: List<ApiExercise> = listOf(),
)

sealed interface ExerciseMutateApiState {
    object Success : ExerciseMutateApiState

    object Error : ExerciseMutateApiState

    object Loading : ExerciseMutateApiState
}
