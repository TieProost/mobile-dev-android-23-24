package com.tieproost.fitnessapp.network.model

import kotlinx.serialization.Serializable

/**
 * Represents a list of exercises received from remote API.
 *
 * @property exercises [List] of [ApiExercise].
 */
@Serializable
data class ApiWorkout(
    val exercises: List<ApiExercise>,
)
