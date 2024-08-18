package com.tieproost.fitnessapp.model

import com.tieproost.fitnessapp.data.database.model.MealType
import java.time.LocalDate

/**
 * Represents a food domain object.
 *
 * @property date The date when the food was logged.
 * @property name Name of the food.
 * @property meal [MealType] of the food.
 * @property servingUnit Unit of the serving.
 * @property servingQty Quantity of food servings.
 * @property calories Amount of calories of the food.
 * @property photo URL for the thumbnail of the food.
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
