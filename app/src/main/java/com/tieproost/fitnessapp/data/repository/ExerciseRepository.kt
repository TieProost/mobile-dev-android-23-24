package com.tieproost.fitnessapp.data.repository

import com.tieproost.fitnessapp.data.database.dao.ExerciseDao
import com.tieproost.fitnessapp.data.database.model.asDbExercise
import com.tieproost.fitnessapp.data.database.model.asDomainExercises
import com.tieproost.fitnessapp.model.Exercise
import com.tieproost.fitnessapp.network.FitnessApiService
import com.tieproost.fitnessapp.network.model.ApiExercise
import com.tieproost.fitnessapp.network.model.ApiWorkout
import com.tieproost.fitnessapp.network.model.RequestBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

interface ExerciseRepository {
    suspend fun searchWorkout(query: String): Flow<ApiWorkout>

    suspend fun insertExercise(exercise: ApiExercise)

    fun getExercises(): Flow<List<Exercise>>

    fun getTotalCalories(): Flow<Double>
}

/**
 * Implementation of [ExerciseRepository] that caches articles using a local database
 * (represented by [exerciseDao]) and fetches data from a remote source (represented by [fitnessApiService]).
 *
 * @param fitnessApiService The API service for fetching remote data from Nutrionix.
 * @param exerciseDao The data access object for the local database.
 */
class CachingExerciseRepository(
    private val fitnessApiService: FitnessApiService,
    private val exerciseDao: ExerciseDao,
) : ExerciseRepository {
    override suspend fun searchWorkout(query: String): Flow<ApiWorkout> =
        flow {
            emit(fitnessApiService.searchWorkout(RequestBody(query)))
        }

    override suspend fun insertExercise(exercise: ApiExercise) {
        exerciseDao.insert(exercise.asDbExercise())
    }

    override fun getExercises(): Flow<List<Exercise>> =
        exerciseDao.getAllItems().map {
            it.asDomainExercises()
        }

    override fun getTotalCalories(): Flow<Double> = exerciseDao.getTotalCalories()
}
