package com.tieproost.fitnessapp.model

import java.time.LocalDate

/**
 * Represents an exercise domain object.
 *
 * @property date The date when the exercise was logged.
 * @property name Name of the exercise.
 * @property durationMinutes Duration of the exercise in minutes.
 * @property calories Amount of calories burned with exercise.
 * @property photo URL for the thumbnail of the exercise.
 */
data class Exercise(
    val date: LocalDate,
    val name: String,
    val durationMinutes: Int,
    val calories: Double,
    val photo: String,
)
