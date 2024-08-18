package com.tieproost.fitnessapp.model

import java.time.LocalDate

/**
 * Represents a settings domain object.
 *
 * @property birthDate The birthdate of the user.
 * @property sex Sex of the user.
 * @property weight Weight of the user.
 * @property height Height of the user.
 * @property calorieGoal Daily goal for calorie intake.
 */
data class Settings(
    val birthDate: LocalDate? = null,
    val sex: Boolean? = null,
    val weight: Double? = null,
    val height: Int? = null,
    val calorieGoal: Int = 2000,
)
