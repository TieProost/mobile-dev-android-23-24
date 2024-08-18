package com.tieproost.fitnessapp.network.model

import kotlinx.serialization.Serializable

/**
 * Represents a photo received from remote API.
 *
 * @param thumb Thumbnail photo URL.
 * @param highres High resolution photo URL.
 */
@Serializable
data class ApiPhoto(
    val thumb: String,
    val highres: String,
)
