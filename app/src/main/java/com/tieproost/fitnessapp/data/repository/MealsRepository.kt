package com.tieproost.fitnessapp.data.repository

import android.content.Context
import com.tieproost.fitnessapp.data.database.FoodDao
import com.tieproost.fitnessapp.data.database.asDbFood
import com.tieproost.fitnessapp.network.FitnessApiService
import com.tieproost.fitnessapp.network.model.ApiFood
import com.tieproost.fitnessapp.network.model.ApiMeal
import com.tieproost.fitnessapp.network.model.RequestBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MealsRepository {
    suspend fun searchMeal(query: String): Flow<ApiMeal>

    suspend fun insertFood(food: ApiFood)
}

class CachingMealsRepository(
    private val fitnessApiService: FitnessApiService,
    private val foodDao: FoodDao,
    context: Context,
) : MealsRepository {
    override suspend fun searchMeal(query: String): Flow<ApiMeal> =
        flow {
            emit(fitnessApiService.getMeal(RequestBody(query)))
        }

    override suspend fun insertFood(food: ApiFood) {
        foodDao.insert(food.asDbFood())
    }
}
