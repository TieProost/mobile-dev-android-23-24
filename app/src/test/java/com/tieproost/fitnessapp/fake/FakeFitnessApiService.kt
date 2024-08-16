package com.tieproost.fitnessapp.fake

import com.tieproost.fitnessapp.network.FitnessApiService
import com.tieproost.fitnessapp.network.model.ApiMeal
import com.tieproost.fitnessapp.network.model.ApiWorkout
import com.tieproost.fitnessapp.network.model.RequestBody

class FakeFitnessApiService : FitnessApiService {
    override suspend fun searchMeal(body: RequestBody): ApiMeal = FakeDataSource.apiMeal

    override suspend fun searchWorkout(body: RequestBody): ApiWorkout = FakeDataSource.apiWorkout
}
