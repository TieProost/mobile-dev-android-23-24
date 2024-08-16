package com.tieproost.fitnessapp.fake.repository

import com.tieproost.fitnessapp.data.database.model.MealType
import com.tieproost.fitnessapp.data.repository.MealsRepository
import com.tieproost.fitnessapp.fake.FakeDataSource
import com.tieproost.fitnessapp.model.Food
import com.tieproost.fitnessapp.network.model.ApiFood
import com.tieproost.fitnessapp.network.model.ApiMeal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMealsRepository : MealsRepository {
    override suspend fun searchMeal(query: String): Flow<ApiMeal> {
        TODO("Not yet implemented")
    }

    override suspend fun insertFood(
        food: ApiFood,
        mealType: MealType,
    ) {
        TODO("Not yet implemented")
    }

    override fun getMeals(): Flow<List<Food>> =
        flow {
            emit(listOf())
        }

    override fun getTotalCalories(): Flow<Double> =
        flow {
            emit(FakeDataSource.totalFoodCalories)
        }
}
