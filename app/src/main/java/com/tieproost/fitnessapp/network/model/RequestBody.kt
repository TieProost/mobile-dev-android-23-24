package com.tieproost.fitnessapp.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RequestBody(
    var query: String,
)
