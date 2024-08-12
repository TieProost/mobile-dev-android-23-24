package com.tieproost.fitnessapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tieproost.fitnessapp.model.Food
import com.tieproost.fitnessapp.network.model.ApiFood
import java.time.LocalDate

@Entity(tableName = "foods")
data class DbFood(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: LocalDate,
    val meal: MealType,
    val name: String,
    val servingQty: Int,
    val servingUnit: String,
    val calories: Double,
    val photo: String,
)

fun ApiFood.asDbFood(): DbFood =
    DbFood(
        date = LocalDate.now(),
        meal = MealType.Breakfast,
        name = food_name,
        servingQty = serving_qty,
        servingUnit = serving_unit,
        calories = nf_calories,
        photo = photo.thumb,
    )

fun List<DbFood>.asDomainFoods(): List<Food> =
    this.map {
        Food(
            id = it.id,
            date = it.date,
            meal = it.meal,
            name = it.name,
            servingQty = it.servingQty,
            servingUnit = it.servingUnit,
            calories = it.calories,
            photo = it.photo,
        )
    }
