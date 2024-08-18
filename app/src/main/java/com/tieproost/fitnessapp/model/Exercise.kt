package com.tieproost.fitnessapp.model

import java.time.LocalDate

/**
 * Represents an exercise domain object.
 *
 * @param date The date when the exercise was logged.
 * @param name Name of the exercise.
 * @param durationMinutes Duration of the exercise in minutes.
 * @param calories Amount of calories burned with exercise.
 * @param photo URL for the thumbnail of the exercise.
 */
data class Exercise(
    val date: LocalDate,
    val name: String,
    val durationMinutes: Int,
    val calories: Double,
    val photo: String,
)
