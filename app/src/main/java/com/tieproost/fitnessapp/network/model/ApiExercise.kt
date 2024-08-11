package com.tieproost.fitnessapp.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiExercise(
    val name: String,
    val duration_min: Int,
    val nf_calories: Double,
    val photo: ApiPhoto,
)
