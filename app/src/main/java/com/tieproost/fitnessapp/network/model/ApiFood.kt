package com.tieproost.fitnessapp.network.model

import kotlinx.serialization.Serializable

/**
 * Represents a food received from remote API.
 *
 * @param food_name Name of the food.
 * @param serving_unit Unit of the serving.
 * @param serving_qty Quantity of food servings.
 * @param nf_calories Amount of calories of the food.
 * @param consumed_at The date when the food was logged.
 * @param photo URL for the thumbnail of the food.
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
