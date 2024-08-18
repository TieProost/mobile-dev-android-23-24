package com.tieproost.fitnessapp.ui.exercise.mutate

import com.tieproost.fitnessapp.network.model.ApiExercise

/**
 * Represents the state of the exercise mutate dialog.
 *
 * @property query The natural language query to search.
 * @property results Results for the searched query.
 */
data class ExerciseMutateState(
    val query: String = "",
    val results: List<ApiExercise> = listOf(),
)

sealed interface ExerciseMutateApiState {
    object Success : ExerciseMutateApiState

    object Error : ExerciseMutateApiState

    object Loading : ExerciseMutateApiState
}
