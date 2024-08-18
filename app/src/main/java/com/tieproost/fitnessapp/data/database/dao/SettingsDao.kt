package com.tieproost.fitnessapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tieproost.fitnessapp.data.database.model.DbSettings
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing settings in the database.
 * This interface provides methods for inserting, updating, deleting and retrieving settings.
 */
@Dao
interface SettingsDao {
    /**
     * Insert a new settings into the database. If there is a conflict, it will be ignored.
     *
     * @param item The [DbSettings] to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbSettings)

    /**
     * Update existing settings.
     *
     * @param item The [DbSettings] to be updates.
     */
    @Update
    suspend fun update(item: DbSettings)

    /**
     * Delete existing settings.
     *
     * @param item The [DbSettings] to be deleted.
     */
    @Delete
    suspend fun delete(item: DbSettings)

    /**
     * Retrieve the only row.
     *
     * @return A [Flow] of [DbSettings], representing the only possible row.
     */
    @Query("SELECT * from settings WHERE id = 0")
    fun get(): Flow<DbSettings?>
}
