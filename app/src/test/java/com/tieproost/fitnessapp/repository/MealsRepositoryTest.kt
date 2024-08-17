package com.tieproost.fitnessapp.repository

import com.tieproost.fitnessapp.data.database.model.MealType
import com.tieproost.fitnessapp.data.database.model.asDomainFood
import com.tieproost.fitnessapp.data.database.model.asDomainFoods
import com.tieproost.fitnessapp.data.repository.CachingMealsRepository
import com.tieproost.fitnessapp.fake.FakeDataSource
import com.tieproost.fitnessapp.fake.FakeFitnessApiService
import com.tieproost.fitnessapp.fake.dao.FakeFoodDao
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class MealsRepositoryTest {
    private lateinit var repository: CachingMealsRepository

    private val newMealType = MealType.Lunch

    @Before
    fun initializeRepository() {
        repository =
            CachingMealsRepository(
                FakeFitnessApiService(),
                FakeFoodDao(FakeDataSource.foods),
            )
    }

    @Test
    fun `getMeals returns all foods`() =
        runTest {
            assertEquals(
                FakeDataSource.foods.asDomainFoods(),
                repository.getMeals().first(),
            )
        }

    @Test
    fun `insertFood inserts food`() =
        runTest {
            val size = repository.getMeals().first().size

            repository.insertFood(FakeDataSource.newFood, newMealType)

            assertEquals(
                FakeDataSource.newFood.asDomainFood(newMealType),
                repository.getMeals().first().last(),
            )

            assertEquals(
                size + 1,
                repository.getMeals().first().size,
            )
        }

    @Test
    fun `getTotalCalories returns correct total calories aggregate`() =
        runTest {
            assertEquals(
                FakeDataSource.foods.sumOf { it.calories },
                repository.getTotalCalories().first(),
            )
        }
}
