package com.tieproost.fitnessapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tieproost.fitnessapp.data.database.model.DbExercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbExercise)

    @Update
    suspend fun update(item: DbExercise)

    @Delete
    suspend fun delete(item: DbExercise)

    @Query("SELECT * FROM exercises WHERE DATE(date) = DATE('now') ORDER BY id ASC")
    fun getAllItems(): Flow<List<DbExercise>>

    @Query("SELECT SUM(calories) FROM exercises WHERE DATE(date) = DATE('now')")
    fun getTotalCalories(): Flow<Double>
}
