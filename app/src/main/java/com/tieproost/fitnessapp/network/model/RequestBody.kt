package com.tieproost.fitnessapp.network.model

import kotlinx.serialization.Serializable

/**
 * Represents a body te send with request to remote API.
 *
 * @param query The query for the API.
 */
@Serializable
data class RequestBody(
    var query: String,
)
