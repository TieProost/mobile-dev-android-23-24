package com.tieproost.fitnessapp.network

import com.tieproost.fitnessapp.network.model.ApiMeal
import com.tieproost.fitnessapp.network.model.ApiWorkout
import com.tieproost.fitnessapp.network.model.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface FitnessApiService {
    @POST("natural/nutrients")
    suspend fun getMeal(
        @Body body: RequestBody,
    ): ApiMeal

    @POST("natural/exercise")
    suspend fun searchWorkout(
        @Body body: RequestBody,
    ): ApiWorkout
}
