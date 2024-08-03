package com.tieproost.fitnessapp.data.repository

import android.content.Context
import com.tieproost.fitnessapp.network.MealsApiService
import com.tieproost.fitnessapp.network.model.ApiMeal
import com.tieproost.fitnessapp.network.model.RequestBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MealsRepository {
    suspend fun getMeal(query: String): Flow<ApiMeal>
}

class CachingMealsRepository(
    private val mealApiService: MealsApiService,
    // private val mealsDao: MealsDao,
    context: Context,
) : MealsRepository {
    override suspend fun getMeal(query: String): Flow<ApiMeal> =
        flow {
            emit(mealApiService.getMeal(RequestBody(query)))
        }
}
