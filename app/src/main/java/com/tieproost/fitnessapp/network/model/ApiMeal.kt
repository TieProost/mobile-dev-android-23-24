package com.tieproost.fitnessapp.network.model

import kotlinx.serialization.Serializable

/**
 * Represents a list of foods received from remote API.
 *
 * @property foods [List] of [ApiFood].
 */
@Serializable
data class ApiMeal(
    val foods: List<ApiFood>,
)
