package com.tieproost.fitnessapp.data.database.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.tieproost.fitnessapp.model.Settings
import java.time.LocalDate

/**
 * Represents an entity in the database for storing settings.
 *
 * @param id The unique identifier. Is always 0.
 * @param birthDate The birthdate of the user.
 * @param sex Sex of the user.
 * @param weight Weight of the user.
 * @param height Height of the user.
 * @param calorieGoal Daily goal for calorie intake.
 */
@Entity(tableName = "settings", indices = [Index(value = ["id"], unique = true)])
data class DbSettings(
    @PrimaryKey
    val id: Int = 0,
    val birthDate: LocalDate? = null,
    val sex: Boolean? = null,
    val weight: Double? = null,
    val height: Int? = null,
    val calorieGoal: Int = 2000,
)

fun Settings.asDbSettings() =
    DbSettings(
        id = 0,
        birthDate,
        sex,
        weight,
        height,
        calorieGoal,
    )

fun DbSettings.asDomainSettings() =
    Settings(
        birthDate,
        sex,
        weight,
        height,
        calorieGoal,
    )
