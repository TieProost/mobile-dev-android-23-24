package com.tieproost.fitnessapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tieproost.fitnessapp.data.database.model.DbFood
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbFood)

    @Update
    suspend fun update(item: DbFood)

    @Delete
    suspend fun delete(item: DbFood)

    @Query("SELECT * from foods ORDER BY id ASC")
    fun getAllItems(): Flow<List<DbFood>>
}
