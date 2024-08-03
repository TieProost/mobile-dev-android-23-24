package com.tieproost.fitnessapp.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiFood(
    val food_name: String,
    val serving_qty: Int,
    val serving_unit: String,
    val nf_calories: Double,
    val consumed_at: String,
    val photo: ApiPhoto,
)
