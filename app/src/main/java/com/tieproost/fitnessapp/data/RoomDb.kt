package com.tieproost.fitnessapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tieproost.fitnessapp.data.database.DbExercise
import com.tieproost.fitnessapp.data.database.DbFood
import com.tieproost.fitnessapp.data.database.ExerciseDao
import com.tieproost.fitnessapp.data.database.FoodDao

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [DbFood::class, DbExercise::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RoomDb : RoomDatabase() {
    abstract fun foodDao(): FoodDao

    abstract fun exerciseDao(): ExerciseDao

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
