package com.tieproost.fitnessapp.data.repository

import android.content.Context
import com.tieproost.fitnessapp.data.database.dao.FoodDao
import com.tieproost.fitnessapp.data.database.model.MealType
import com.tieproost.fitnessapp.data.database.model.asDbFood
import com.tieproost.fitnessapp.data.database.model.asDomainFoods
import com.tieproost.fitnessapp.model.Food
import com.tieproost.fitnessapp.network.FitnessApiService
import com.tieproost.fitnessapp.network.model.ApiFood
import com.tieproost.fitnessapp.network.model.ApiMeal
import com.tieproost.fitnessapp.network.model.RequestBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

interface MealsRepository {
    suspend fun searchMeal(query: String): Flow<ApiMeal>

    suspend fun insertFood(
        food: ApiFood,
        mealType: MealType,
    )

    fun getMeals(): Flow<List<Food>>

    fun getTotalCalories(): Flow<Double>
}

class CachingMealsRepository(
    private val fitnessApiService: FitnessApiService,
    private val foodDao: FoodDao,
    context: Context,
) : MealsRepository {
    override suspend fun searchMeal(query: String): Flow<ApiMeal> =
        flow {
            emit(fitnessApiService.searchMeal(RequestBody(query)))
        }

    override suspend fun insertFood(
        food: ApiFood,
        mealType: MealType,
    ) {
        foodDao.insert(food.asDbFood(mealType))
    }

    override fun getMeals(): Flow<List<Food>> =
        foodDao.getAllItems().map {
            it.asDomainFoods()
        }

    override fun getTotalCalories(): Flow<Double> = foodDao.getTotalCalories()
}
