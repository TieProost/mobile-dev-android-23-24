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
import junit.framework.TestCase.assertNotSame
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
            calories = 543.1,
            photo = "",
            date = LocalDate.now(),
        )

    private val exercises =
        listOf(
            exercise,
            Exercise(
                name = "walk",
                durationMinutes = 125,
                calories = 323.2,
                photo = "",
                date = LocalDate.now(),
            ),
        )

    private val newTestName = "newExerciseTestName"

    private suspend fun addOneToDb(exercise: Exercise) {
        exerciseDao.insert(exercise.asDbExercise())
    }

    private suspend fun addMultipleToDb(exercises: List<Exercise>) {
        exercises.forEach { addOneToDb(it) }
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
            addOneToDb(exercise)
            val allItems = exerciseDao.getAllItems().first()
            assertEquals(exercise, allItems[0].asDomainExercise())
        }

    @Test
    @Throws(Exception::class)
    fun daoUpdateExercise_updatesExercise() =
        runBlocking {
            addOneToDb(exercise)
            val allItems = exerciseDao.getAllItems().first()
            assertNotSame(newTestName, allItems.first().name)

            exerciseDao.update(allItems.first().copy(name = newTestName))

            val actualName = exerciseDao.getAllItems().first()[0].name

            assertEquals(newTestName, actualName)
        }

    @Test
    @Throws(Exception::class)
    fun daoDeleteExercise_deletesExercise() =
        runBlocking {
            addOneToDb(exercise)
            val allItems = exerciseDao.getAllItems().first()
            assertEquals(1, allItems.size)

            exerciseDao.delete(allItems[0])

            assertEquals(0, exerciseDao.getAllItems().first().size)
        }

    @Test
    @Throws(Exception::class)
    fun daoGetTotalCalories_returnsCorrectSum() =
        runBlocking {
            addMultipleToDb(exercises)
            val allItems = exerciseDao.getAllItems().first()

            assertEquals(allItems.sumOf { it.calories }, exerciseDao.getTotalCalories().first())
        }
}
