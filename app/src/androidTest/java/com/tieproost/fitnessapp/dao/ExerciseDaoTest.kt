package com.tieproost.fitnessapp.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tieproost.fitnessapp.data.database.RoomDb
import com.tieproost.fitnessapp.data.database.dao.ExerciseDao
import com.tieproost.fitnessapp.data.database.model.asDbExercise
import com.tieproost.fitnessapp.data.database.model.asDomainExercise
import com.tieproost.fitnessapp.model.Exercise
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class ExerciseDaoTest {
    private lateinit var exerciseDao: ExerciseDao
    private lateinit var roomDb: RoomDb

    private var exercise =
        Exercise(
            name = "run",
            durationMinutes = 60,
            calories = 500.0,
            photo = "",
            date = LocalDate.now(),
        )

    private suspend fun addOneToDb() {
        exerciseDao.insert(exercise.asDbExercise())
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        roomDb =
            Room
                .inMemoryDatabaseBuilder(context, RoomDb::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
        exerciseDao = roomDb.exerciseDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        roomDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsertExercise_insertsIntoDb() =
        runBlocking {
            addOneToDb()
            val allItems = exerciseDao.getAllItems().first()
            assertEquals(exercise, allItems[0].asDomainExercise())
        }
}
