package com.tieproost.fitnessapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.tieproost.fitnessapp.data.database.entities.DbExercise

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
