package com.tieproost.fitnessapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tieproost.fitnessapp.data.database.model.DbSettings
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbSettings)

    @Update
    suspend fun update(item: DbSettings)

    @Query("SELECT * from settings WHERE id = 0")
    fun get(): Flow<DbSettings?>
}
