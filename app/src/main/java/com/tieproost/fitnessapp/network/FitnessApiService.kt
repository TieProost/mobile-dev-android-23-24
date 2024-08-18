package com.tieproost.fitnessapp.network

import com.tieproost.fitnessapp.network.model.ApiMeal
import com.tieproost.fitnessapp.network.model.ApiWorkout
import com.tieproost.fitnessapp.network.model.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Interface for interacting with the API.
 */
interface FitnessApiService {
    /**
     * Searches for a list of foods matching the natural language input query.
     *
     * @return An [ApiMeal] object containing foods.
     * @param body The [RequestBody] containing the query for the search.
     */
    @POST("natural/nutrients")
    suspend fun searchMeal(
        @Body body: RequestBody,
    ): ApiMeal

    /**
     * Searches for a list of exercises matching the natural language input query.
     *
     * @return An [ApiWorkout] object containing foods.
     * @param body The [RequestBody] containing the query for the search.
     */
    @POST("natural/exercise")
    suspend fun searchWorkout(
        @Body body: RequestBody,
    ): ApiWorkout
}
