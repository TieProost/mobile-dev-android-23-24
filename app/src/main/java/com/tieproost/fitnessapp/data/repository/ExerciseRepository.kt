package com.tieproost.fitnessapp.data.repository

import android.content.Context
import com.tieproost.fitnessapp.data.database.dao.ExerciseDao
import com.tieproost.fitnessapp.data.database.model.asDbExercise
import com.tieproost.fitnessapp.network.FitnessApiService
import com.tieproost.fitnessapp.network.model.ApiExercise
import com.tieproost.fitnessapp.network.model.ApiWorkout
import com.tieproost.fitnessapp.network.model.RequestBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ExerciseRepository {
    suspend fun searchWorkout(query: String): Flow<ApiWorkout>

    suspend fun insertExercise(exercise: ApiExercise)
}

class CachingExerciseRepository(
    private val fitnessApiService: FitnessApiService,
    private val exerciseDao: ExerciseDao,
    context: Context,
) : ExerciseRepository {
    override suspend fun searchWorkout(query: String): Flow<ApiWorkout> =
        flow {
            emit(fitnessApiService.searchWorkout(RequestBody(query)))
        }

    override suspend fun insertExercise(exercise: ApiExercise) {
        exerciseDao.insert(exercise.asDbExercise())
    }
}
