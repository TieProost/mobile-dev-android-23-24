package com.tieproost.fitnessapp.ui.util

fun nullableToString(value: Any?): String = (value ?: "").toString()

fun booleanToSexString(bool: Boolean?): String =
    when (bool) {
        true -> "Male"
        false -> "Female"
        null -> " - "
    }

fun sexStringToBoolean(string: String): Boolean? =
    when (string) {
        "Male" -> true
        "Female" -> false
        else -> {
            null
        }
    }
