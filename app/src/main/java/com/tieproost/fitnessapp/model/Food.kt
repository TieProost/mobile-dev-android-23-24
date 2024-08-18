package com.tieproost.fitnessapp.model

import com.tieproost.fitnessapp.data.database.model.MealType
import java.time.LocalDate

/**
 * Represents a food domain object.
 *
 * @param date The date when the food was logged.
 * @param name Name of the food.
 * @param meal [MealType] of the food.
 * @param servingUnit Unit of the serving.
 * @param servingQty Quantity of food servings.
 * @param calories Amount of calories of the food.
 * @param photo URL for the thumbnail of the food.
 */
data class Food(
    val date: LocalDate,
    val meal: MealType,
    val name: String,
    val servingQty: Int,
    val servingUnit: String,
    val calories: Double,
    val photo: String,
)
