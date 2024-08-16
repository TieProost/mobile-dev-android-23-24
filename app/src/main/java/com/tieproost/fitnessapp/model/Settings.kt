package com.tieproost.fitnessapp.model

import java.time.LocalDate

data class Settings(
    val birthDate: LocalDate? = null,
    val sex: Boolean? = null,
    val weight: Double? = null,
    val height: Int? = null,
    val calorieGoal: Int = 2000,
)
