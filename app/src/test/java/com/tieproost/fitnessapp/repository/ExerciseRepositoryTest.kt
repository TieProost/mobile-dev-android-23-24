package com.tieproost.fitnessapp.repository

import com.tieproost.fitnessapp.data.database.model.asDomainExercise
import com.tieproost.fitnessapp.data.database.model.asDomainExercises
import com.tieproost.fitnessapp.data.repository.CachingExerciseRepository
import com.tieproost.fitnessapp.fake.FakeDataSource
import com.tieproost.fitnessapp.fake.FakeFitnessApiService
import com.tieproost.fitnessapp.fake.dao.FakeExerciseDao
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ExerciseRepositoryTest {
    private lateinit var repository: CachingExerciseRepository

    @Before
    fun initializeRepository() {
        repository =
            CachingExerciseRepository(
                FakeFitnessApiService(),
                FakeExerciseDao(FakeDataSource.exercises),
            )
    }

    @Test
    fun `getExercises returns all exercises`() =
        runTest {
            assertEquals(
                FakeDataSource.exercises.asDomainExercises(),
                repository.getExercises().first(),
            )
        }

    @Test
    fun `insertExercise inserts exercise`() =
        runTest {
            val size = repository.getExercises().first().size

            repository.insertExercise(FakeDataSource.newExercise)

            assertEquals(
                FakeDataSource.newExercise.asDomainExercise(),
                repository.getExercises().first().last(),
            )

            assertEquals(
                size + 1,
                repository.getExercises().first().size,
            )
        }

    @Test
    fun `getTotalCalories returns correct total calories aggregate`() =
        runTest {
            assertEquals(
                FakeDataSource.exercises.sumOf { it.calories },
                repository.getTotalCalories().first(),
            )
        }
}
