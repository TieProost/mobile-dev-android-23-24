package com.tieproost.fitnessapp.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiWorkout(
    val exercises: List<ApiExercise>,
)
