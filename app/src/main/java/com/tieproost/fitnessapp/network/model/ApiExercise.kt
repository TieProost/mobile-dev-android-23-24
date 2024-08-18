package com.tieproost.fitnessapp.network.model

import kotlinx.serialization.Serializable

/**
 * Represents an exercise received from remote API.
 *
 * @param name Name of the exercise.
 * @param duration_min Duration of the exercise in minutes.
 * @param nf_calories Amount of calories burned with exercise.
 * @param photo [ApiPhoto] for the thumbnail of the exercise.
 */
@Serializable
data class ApiExercise(
    val name: String,
    val duration_min: Int,
    val nf_calories: Double,
    val photo: ApiPhoto,
)
