package com.tieproost.fitnessapp.model

import com.tieproost.fitnessapp.data.database.MealType
import java.time.LocalDate

data class Food(
    val id: Int,
    val date: LocalDate,
    val meal: MealType,
    val name: String,
    val servingQty: Int,
    val servingUnit: String,
    val calories: Double,
    val photo: String,
)
