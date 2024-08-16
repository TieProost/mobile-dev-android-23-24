package com.tieproost.fitnessapp.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tieproost.fitnessapp.data.database.RoomDb
import com.tieproost.fitnessapp.data.database.dao.FoodDao
import com.tieproost.fitnessapp.data.database.model.MealType
import com.tieproost.fitnessapp.data.database.model.asDbFood
import com.tieproost.fitnessapp.data.database.model.asDomainFood
import com.tieproost.fitnessapp.model.Food
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class FoodDaoTest {
    private lateinit var foodDao: FoodDao
    private lateinit var roomDb: RoomDb

    private var food =
        Food(
            name = "apple",
            servingQty = 2,
            servingUnit = "medium",
            calories = 50.0,
            photo = "",
            date = LocalDate.now(),
            meal = MealType.Breakfast,
        )

    private suspend fun addOneToDb() {
        foodDao.insert(food.asDbFood())
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
        foodDao = roomDb.foodDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        roomDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsertFood_insertsIntoDb() =
        runBlocking {
            addOneToDb()
            val allItems = foodDao.getAllItems().first()
            assertEquals(food, allItems[0].asDomainFood())
        }
}
