package com.tieproost.fitnessapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tieproost.fitnessapp.network.model.ApiExercise
import java.time.LocalDate

@Entity(tableName = "exercises")
data class DbExercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: LocalDate,
    val meal: MealType,
    val name: String,
    val calories: Double,
    val photo: String,
)

fun ApiExercise.asDbExercise(): DbExercise =
    DbExercise(
        name = name,
        date = LocalDate.now(),
        meal = MealType.Breakfast,
        calories = nf_calories,
        photo = photo.thumb,
    )
