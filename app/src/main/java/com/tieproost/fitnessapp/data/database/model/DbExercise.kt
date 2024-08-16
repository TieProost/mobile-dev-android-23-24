package com.tieproost.fitnessapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tieproost.fitnessapp.model.Exercise
import com.tieproost.fitnessapp.network.model.ApiExercise
import java.time.LocalDate

@Entity(tableName = "exercises")
data class DbExercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: LocalDate,
    val name: String,
    val durationMinutes: Int,
    val calories: Double,
    val photo: String,
)

fun DbExercise.asDomainExercise(): Exercise =
    Exercise(
        date = date,
        name = name,
        calories = calories,
        photo = photo,
        durationMinutes = durationMinutes,
    )

fun List<DbExercise>.asDomainExercises(): List<Exercise> =
    this.map {
        it.asDomainExercise()
    }

fun ApiExercise.asDbExercise(): DbExercise =
    DbExercise(
        name = name,
        date = LocalDate.now(),
        durationMinutes = duration_min,
        calories = nf_calories,
        photo = photo.thumb,
    )

fun ApiExercise.asDomainExercise(): Exercise =
    Exercise(
        date = LocalDate.now(),
        name = name,
        durationMinutes = duration_min,
        calories = nf_calories,
        photo = photo.thumb,
    )

fun Exercise.asDbExercise(): DbExercise =
    DbExercise(
        date = date,
        name = name,
        calories = calories,
        photo = photo,
        durationMinutes = durationMinutes,
    )
