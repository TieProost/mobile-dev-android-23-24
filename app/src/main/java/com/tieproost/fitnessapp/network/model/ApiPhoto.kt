package com.tieproost.fitnessapp.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiPhoto(
    val thumb: String,
    val highres: String,
)
