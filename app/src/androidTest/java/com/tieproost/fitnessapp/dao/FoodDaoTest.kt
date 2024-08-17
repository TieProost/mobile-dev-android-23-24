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
import junit.framework.TestCase
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
            calories = 54.1,
            photo = "",
            date = LocalDate.now(),
            meal = MealType.Breakfast,
        )

    private val foods =
        listOf(
            food,
            Food(
                name = "cookie",
                servingQty = 3,
                servingUnit = "small",
                calories = 321.3,
                photo = "",
                date = LocalDate.now(),
                meal = MealType.Lunch,
            ),
        )

    private val newTestName = "newFoodTestName"

    private suspend fun addOneToDb(food: Food) {
        foodDao.insert(food.asDbFood())
    }

    private suspend fun addMultipleToDb(foods: List<Food>) {
        foods.forEach { addOneToDb(it) }
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
            addOneToDb(food)
            val allItems = foodDao.getAllItems().first()
            assertEquals(food, allItems[0].asDomainFood())
        }

    @Test
    @Throws(Exception::class)
    fun daoUpdateFood_updatesFood() =
        runBlocking {
            addOneToDb(food)
            val allItems = foodDao.getAllItems().first()
            TestCase.assertNotSame(newTestName, allItems.first().name)

            foodDao.update(allItems.first().copy(name = newTestName))

            val actualName = foodDao.getAllItems().first()[0].name

            assertEquals(newTestName, actualName)
        }

    @Test
    @Throws(Exception::class)
    fun daoDeleteFood_deletesFood() =
        runBlocking {
            addOneToDb(food)
            val allItems = foodDao.getAllItems().first()
            assertEquals(1, allItems.size)

            foodDao.delete(allItems[0])

            assertEquals(0, foodDao.getAllItems().first().size)
        }

    @Test
    @Throws(Exception::class)
    fun daoGetTotalCalories_returnsCorrectSum() =
        runBlocking {
            addMultipleToDb(foods)
            val allItems = foodDao.getAllItems().first()

            assertEquals(allItems.sumOf { it.calories }, foodDao.getTotalCalories().first())
        }
}
