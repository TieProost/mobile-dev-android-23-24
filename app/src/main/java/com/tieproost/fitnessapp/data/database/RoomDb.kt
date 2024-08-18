package com.tieproost.fitnessapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tieproost.fitnessapp.data.database.dao.ExerciseDao
import com.tieproost.fitnessapp.data.database.dao.FoodDao
import com.tieproost.fitnessapp.data.database.dao.SettingsDao
import com.tieproost.fitnessapp.data.database.model.DbExercise
import com.tieproost.fitnessapp.data.database.model.DbFood
import com.tieproost.fitnessapp.data.database.model.DbSettings

/**
 * Database class with a singleton instance object.
 *
 * @property foodDao The Data Access Object (DAO) for articles.
 * @property exerciseDao The Data Access Object (DAO) for study locations.
 * @property settingsDao The Data Access Object (DAO) for car parks.
 */
@Database(entities = [DbFood::class, DbExercise::class, DbSettings::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RoomDb : RoomDatabase() {
    abstract fun foodDao(): FoodDao

    abstract fun exerciseDao(): ExerciseDao

    abstract fun settingsDao(): SettingsDao

    companion object {
        @Volatile
        private var instance: RoomDb? = null

        fun getDatabase(context: Context): RoomDb {
            // if the Instance is not null, return it, otherwise create a new database instance.

            return instance ?: synchronized(this) {
                Room
                    .databaseBuilder(context, RoomDb::class.java, "fitness_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}
