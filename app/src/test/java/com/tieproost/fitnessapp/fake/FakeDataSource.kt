package com.tieproost.fitnessapp.fake

import com.tieproost.fitnessapp.data.database.model.DbExercise
import com.tieproost.fitnessapp.data.database.model.DbFood
import com.tieproost.fitnessapp.data.database.model.MealType
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

    val foods: List<DbFood> =
        listOf(
            DbFood(
                id = 1,
                name = "apple",
                servingQty = 2,
                servingUnit = "medium",
                calories = 50.0,
                photo = "",
                date = LocalDate.now(),
                meal = MealType.Breakfast,
            ),
            DbFood(
                id = 2,
                name = "cookie",
                servingQty = 3,
                servingUnit = "small",
                calories = 300.0,
                photo = "",
                date = LocalDate.now(),
                meal = MealType.Snacks,
            ),
        )

    val newFood =
        ApiFood(
            food_name = "steak",
            serving_qty = 1,
            serving_unit = "big",
            nf_calories = 312.5,
            photo = apiPhoto,
            consumed_at = LocalDate.now().toString(),
        )

    val exercises =
        listOf(
            DbExercise(
                id = 1,
                name = "running",
                durationMinutes = 60,
                calories = 400.0,
                photo = "",
                date = LocalDate.now(),
            ),
            DbExercise(
                id = 2,
                name = "biking",
                durationMinutes = 30,
                calories = 150.0,
                photo = "",
                date = LocalDate.now(),
            ),
        )

    val newExercise =
        ApiExercise(
            name = "biking",
            duration_min = 120,
            nf_calories = 1234.0,
            photo = apiPhoto,
        )

    val settings =
        Settings(
            sex = true,
            birthDate = LocalDate.now().minusYears(20),
            height = 180,
            weight = 80.0,
            calorieGoal = 2500,
        )

    val newSettings: Settings =
        Settings(
            sex = false,
            birthDate = LocalDate.now().minusYears(41),
            height = 150,
            weight = 65.2,
            calorieGoal = 1800,
        )
}
