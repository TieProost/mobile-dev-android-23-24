package com.tieproost.fitnessapp.fake.dao

import com.tieproost.fitnessapp.data.database.dao.FoodDao
import com.tieproost.fitnessapp.data.database.model.DbFood
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFoodDao(
    initialFoods: List<DbFood>? = emptyList(),
) : FoodDao {
    private var _foods: MutableMap<Int, DbFood>? = null

    var foods: List<DbFood>?
        get() = _foods?.values?.toList()
        set(newFoods) {
            _foods = newFoods?.associateBy { it.id }?.toMutableMap()
        }

    init {
        foods = initialFoods
    }

    override suspend fun insert(item: DbFood) {
        foods = foods?.plus(item)
    }

    override suspend fun update(item: DbFood) {
    }

    override suspend fun delete(item: DbFood) {
        foods = foods?.minus(item)
    }

    override fun getAllItems(): Flow<List<DbFood>> =
        flow {
            emit(foods ?: emptyList())
        }

    override fun getTotalCalories(): Flow<Double> =
        flow {
            emit(foods?.sumOf { it.calories } ?: 0.0)
        }
}
