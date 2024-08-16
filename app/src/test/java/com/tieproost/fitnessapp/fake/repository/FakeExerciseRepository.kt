package com.tieproost.fitnessapp.fake.repository

import com.tieproost.fitnessapp.data.repository.ExerciseRepository
import com.tieproost.fitnessapp.fake.FakeDataSource
import com.tieproost.fitnessapp.model.Exercise
import com.tieproost.fitnessapp.network.model.ApiExercise
import com.tieproost.fitnessapp.network.model.ApiWorkout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeExerciseRepository : ExerciseRepository {
    override suspend fun searchWorkout(query: String): Flow<ApiWorkout> {
        TODO("Not yet implemented")
    }

    override suspend fun insertExercise(exercise: ApiExercise) {
        TODO("Not yet implemented")
    }

    override fun getExercises(): Flow<List<Exercise>> =
        flow {
            emit(listOf()) // todo
        }

    override fun getTotalCalories(): Flow<Double> =
        flow {
            emit(FakeDataSource.totalExerciseCalories)
        }
}
