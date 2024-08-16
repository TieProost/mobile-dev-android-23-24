package com.tieproost.fitnessapp.model

import java.time.LocalDate

data class Exercise(
    val date: LocalDate,
    val name: String,
    val durationMinutes: Int,
    val calories: Double,
    val photo: String,
)
