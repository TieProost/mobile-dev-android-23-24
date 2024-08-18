package com.tieproost.fitnessapp.network.model

import kotlinx.serialization.Serializable

/**
 * Represents a food received from remote API.
 *
 * @property food_name Name of the food.
 * @property serving_unit Unit of the serving.
 * @property serving_qty Quantity of food servings.
 * @property nf_calories Amount of calories of the food.
 * @property consumed_at The date when the food was logged.
 * @property photo URL for the thumbnail of the food.
 */
@Serializable
data class ApiFood(
    val food_name: String,
    val serving_qty: Int,
    val serving_unit: String,
    val nf_calories: Double,
    val consumed_at: String,
    val photo: ApiPhoto,
)
