package com.tieproost.fitnessapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbFood)

    @Update
    suspend fun update(item: DbFood)

    @Delete
    suspend fun delete(item: DbFood)

//    @Query("SELECT * from foods ORDER BY name ASC")
//    fun getAllItems(): Flow<List<DbMeals>>
}
