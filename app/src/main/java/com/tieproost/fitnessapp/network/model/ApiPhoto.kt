package com.tieproost.fitnessapp.network.model

import kotlinx.serialization.Serializable

/**
 * Represents a photo received from remote API.
 *
 * @property thumb Thumbnail photo URL.
 * @property highres High resolution photo URL.
 */
@Serializable
data class ApiPhoto(
    val thumb: String,
    val highres: String,
)
