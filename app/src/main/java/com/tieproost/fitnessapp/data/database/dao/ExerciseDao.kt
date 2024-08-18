package com.tieproost.fitnessapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tieproost.fitnessapp.data.database.model.DbExercise
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing exercises in the database.
 * This interface provides methods for inserting, updating, deleting and retrieving exercises.
 */
@Dao
interface ExerciseDao {
    /**
     * Insert a new exercise into the database. If there is a conflict, it will be ignored.
     *
     * @param item The [DbExercise] to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbExercise)

    /**
     * Update existing exercise.
     *
     * @param item The [DbExercise] to be updates.
     */
    @Update
    suspend fun update(item: DbExercise)

    /**
     * Delete existing exercise.
     *
     * @param item The [DbExercise] to be deleted.
     */
    @Delete
    suspend fun delete(item: DbExercise)

    /**
     * Retrieve all exercises where date is today, ordered by id in ascending order.
     *
     * @return A [Flow] of [List] of [DbExercise], representing the list of exercises in the specified order.
     */
    @Query("SELECT * FROM exercises WHERE DATE(date) = DATE('now') ORDER BY id ASC")
    fun getAllItems(): Flow<List<DbExercise>>

    /**
     * Retrieve sum of calories of all exercises where data is today.
     *
     * @return A [Flow] of [Double] , representing the aggregate result.
     */
    @Query("SELECT SUM(calories) FROM exercises WHERE DATE(date) = DATE('now')")
    fun getTotalCalories(): Flow<Double>
}
