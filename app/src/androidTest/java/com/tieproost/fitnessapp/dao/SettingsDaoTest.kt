package com.tieproost.fitnessapp.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tieproost.fitnessapp.data.database.RoomDb
import com.tieproost.fitnessapp.data.database.dao.SettingsDao
import com.tieproost.fitnessapp.data.database.model.DbSettings
import com.tieproost.fitnessapp.data.database.model.asDbSettings
import com.tieproost.fitnessapp.data.database.model.asDomainSettings
import com.tieproost.fitnessapp.model.Settings
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class SettingsDaoTest {
    private lateinit var settingsDao: SettingsDao
    private lateinit var roomDb: RoomDb

    private var settings =
        Settings(
            sex = true,
            birthDate = LocalDate.now().minusYears(20),
            height = 180,
            weight = 80.0,
            calorieGoal = 2500,
        )

    private val newTestHeight = 11

    private suspend fun addOneToDb() {
        settingsDao.insert(settings.asDbSettings())
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        roomDb =
            Room
                .inMemoryDatabaseBuilder(context, RoomDb::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
        settingsDao = roomDb.settingsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        roomDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsertSetting_upsertsIntoDb() =
        runBlocking {
            addOneToDb()
            val retrievedSettings = settingsDao.get().first()
            assertEquals(settings, retrievedSettings?.asDomainSettings())
        }

    @Test
    @Throws(Exception::class)
    fun daoUpdateSettings_updatesSettings() =
        runBlocking {
            addOneToDb()
            val allItems = settingsDao.get().first()
            TestCase.assertNotSame(newTestHeight, allItems?.height)

            settingsDao.update(allItems?.copy(height = newTestHeight) ?: DbSettings())

            val actualHeight = settingsDao.get().first()?.height

            assertEquals(newTestHeight, actualHeight)
        }

    @Test
    @Throws(Exception::class)
    fun daoDeleteSettings_deletesSettings() =
        runBlocking {
            addOneToDb()
            val allItems = settingsDao.get().first()
            assertEquals(settings, allItems?.asDomainSettings())

            if (allItems != null) settingsDao.delete(allItems)

            assertNull(settingsDao.get().first())
        }
}
