package com.tieproost.fitnessapp.fake.dao

import com.tieproost.fitnessapp.data.database.dao.ExerciseDao
import com.tieproost.fitnessapp.data.database.model.DbExercise
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeExerciseDao(
    initialExercises: List<DbExercise>? = emptyList(),
) : ExerciseDao {
    private var _exercises: MutableMap<Int, DbExercise>? = null

    var exercises: List<DbExercise>?
        get() = _exercises?.values?.toList()
        set(newExercises) {
            _exercises = newExercises?.associateBy { it.id }?.toMutableMap()
        }

    init {
        exercises = initialExercises
    }

    override suspend fun insert(item: DbExercise) {
        exercises = exercises?.plus(item)
    }

    override suspend fun update(item: DbExercise) {
    }

    override suspend fun delete(item: DbExercise) {
        exercises = exercises?.minus(item)
    }

    override fun getAllItems(): Flow<List<DbExercise>> =
        flow {
            emit(exercises ?: emptyList())
        }

    override fun getTotalCalories(): Flow<Double> =
        flow {
            emit(exercises?.sumOf { it.calories } ?: 0.0)
        }
}
