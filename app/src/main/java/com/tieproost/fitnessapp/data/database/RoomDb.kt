package com.tieproost.fitnessapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tieproost.fitnessapp.data.database.dao.ExerciseDao
import com.tieproost.fitnessapp.data.database.dao.FoodDao
import com.tieproost.fitnessapp.data.database.dao.SettingsDao
import com.tieproost.fitnessapp.data.database.model.DbExercise
import com.tieproost.fitnessapp.data.database.model.DbFood
import com.tieproost.fitnessapp.data.database.model.DbSettings
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

/**
 * Database class with a singleton Instance object.
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
                    .addCallback(seedDatabaseCallback(context))
                    .build()
                    .also { instance = it }
            }
        }

        @OptIn(DelicateCoroutinesApi::class)
        private fun seedDatabaseCallback(context: Context): Callback =
            object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Executors.newSingleThreadExecutor().execute {
                        val settingsDao = getDatabase(context).settingsDao()
                        GlobalScope.launch {
                            settingsDao.insert(DbSettings())
                        }
                    }
                }
            }
    }
}
