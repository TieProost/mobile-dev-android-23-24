package com.tieproost.fitnessapp.model

import java.time.LocalDate

/**
 * Represents a settings domain object.
 *
 * @param birthDate The birthdate of the user.
 * @param sex Sex of the user.
 * @param weight Weight of the user.
 * @param height Height of the user.
 * @param calorieGoal Daily goal for calorie intake.
 */
data class Settings(
    val birthDate: LocalDate? = null,
    val sex: Boolean? = null,
    val weight: Double? = null,
    val height: Int? = null,
    val calorieGoal: Int = 2000,
)
