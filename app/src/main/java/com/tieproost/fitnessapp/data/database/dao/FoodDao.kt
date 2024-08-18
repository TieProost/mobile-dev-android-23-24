package com.tieproost.fitnessapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tieproost.fitnessapp.data.database.model.DbFood
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing foods in the database.
 * This interface provides methods for inserting, updating, deleting and retrieving food.
 */
@Dao
interface FoodDao {
    /**
     * Insert a new food into the database. If there is a conflict, it will be ignored.
     *
     * @param item The [DbFood] to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbFood)

    /**
     * Update existing food.
     *
     * @param item The [DbFood] to be updates.
     */
    @Update
    suspend fun update(item: DbFood)

    /**
     * Delete existing food.
     *
     * @param item The [DbFood] to be deleted.
     */
    @Delete
    suspend fun delete(item: DbFood)

    /**
     * Retrieve all foods where date is today, ordered by id in ascending order.
     *
     * @return A [Flow] of [List] of [DbFood], representing the list of foods in the specified order.
     */
    @Query("SELECT * FROM foods WHERE DATE(date) = DATE('now') ORDER BY id ASC")
    fun getAllItems(): Flow<List<DbFood>>

    /**
     * Retrieve sum of calories of all foods where data is today.
     *
     * @return A [Flow] of [Double] , representing the aggregate result.
     */
    @Query("SELECT SUM(calories) FROM foods WHERE DATE(date) = DATE('now')")
    fun getTotalCalories(): Flow<Double>
}
