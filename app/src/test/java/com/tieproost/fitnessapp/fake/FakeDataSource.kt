package com.tieproost.fitnessapp.fake

import com.tieproost.fitnessapp.data.database.model.asDbExercise
import com.tieproost.fitnessapp.model.Settings
import com.tieproost.fitnessapp.network.model.ApiExercise
import com.tieproost.fitnessapp.network.model.ApiFood
import com.tieproost.fitnessapp.network.model.ApiMeal
import com.tieproost.fitnessapp.network.model.ApiPhoto
import com.tieproost.fitnessapp.network.model.ApiWorkout
import java.time.LocalDate

object FakeDataSource {
    val totalFoodCalories = 50.0
    val totalExerciseCalories = 500.0

    private val apiPhoto = ApiPhoto(thumb = "", highres = " ")

    val apiMeal =
        ApiMeal(
            foods =
                listOf(
                    ApiFood(
                        food_name = "apple",
                        serving_qty = 1,
                        serving_unit = "medium",
                        nf_calories = 50.0,
                        photo = apiPhoto,
                        consumed_at = " ",
                    ),
                ),
        )

    val apiWorkout =
        ApiWorkout(
            exercises =
                listOf(
                    ApiExercise(
                        name = "running",
                        duration_min = 60,
                        nf_calories = 400.0,
                        photo = apiPhoto,
                    ),
                ),
        )

    val exercises =
        listOf(
            apiWorkout.exercises[0].asDbExercise(),
        )

    val settings =
        Settings(
            sex = true,
            birthDate = LocalDate.now().minusYears(20),
            height = 180,
            weight = 80.0,
            calorieGoal = 2500,
        )
}
