package com.tieproost.fitnessapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbExercise)

    @Update
    suspend fun update(item: DbExercise)

    @Delete
    suspend fun delete(item: DbExercise)

//    @Query("SELECT * from foods ORDER BY name ASC")
//    fun getAllItems(): Flow<List<DbMeals>>
}
