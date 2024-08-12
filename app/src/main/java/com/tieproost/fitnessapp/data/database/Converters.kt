package com.tieproost.fitnessapp.data.database

import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {
    @TypeConverter
    fun toDate(dateString: String?): LocalDate? =
        if (dateString == null) {
            null
        } else {
            LocalDate.parse(dateString)
        }

    @TypeConverter
    fun toDateString(date: LocalDate?): String? = date?.toString()
}
