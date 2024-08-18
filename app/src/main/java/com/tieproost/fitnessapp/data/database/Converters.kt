package com.tieproost.fitnessapp.data.database

import androidx.room.TypeConverter
import java.time.LocalDate

/**
 * This class provides type converters for Room database to handle conversions between complex data types and their
 * representations in the database.
 */
class Converters {
    /**
     * Converts a [String] representation of a date to a [LocalDate] object.
     *
     * @param dateString The [String] representation of the date.
     * @return The corresponding [LocalDate] or null if the input is null.
     */
    @TypeConverter
    fun toDate(dateString: String?): LocalDate? =
        if (dateString == null) {
            null
        } else {
            LocalDate.parse(dateString)
        }

    /**
     * Converts a [LocalDate] object to its [String] representation.
     *
     * @param date The [LocalDate] object.
     * @return The [String] representation of the date or null if the input is null.
     */
    @TypeConverter
    fun toDateString(date: LocalDate?): String? = date?.toString()
}
