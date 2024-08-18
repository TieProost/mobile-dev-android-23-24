package com.tieproost.fitnessapp.network.model

import kotlinx.serialization.Serializable

/**
 * Represents an exercise received from remote API.
 *
 * @property name Name of the exercise.
 * @property duration_min Duration of the exercise in minutes.
 * @property nf_calories Amount of calories burned with exercise.
 * @property photo [ApiPhoto] for the thumbnail of the exercise.
 */
@Serializable
data class ApiExercise(
    val name: String,
    val duration_min: Int,
    val nf_calories: Double,
    val photo: ApiPhoto,
)
