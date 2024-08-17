package com.tieproost.fitnessapp.data.database.model

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

fun DbFood.asDomainFood(): Food =
    Food(
        date = date,
        meal = meal,
        name = name,
        servingQty = servingQty,
        servingUnit = servingUnit,
        calories = calories,
        photo = photo,
    )

fun Food.asDbFood(): DbFood =
    DbFood(
        date = date,
        meal = meal,
        name = name,
        servingQty = servingQty,
        servingUnit = servingUnit,
        calories = calories,
        photo = photo,
    )

fun List<DbFood>.asDomainFoods(): List<Food> =
    this.map {
        it.asDomainFood()
    }

fun ApiFood.asDbFood(mealType: MealType): DbFood =
    DbFood(
        date = LocalDate.now(),
        meal = mealType,
        name = food_name,
        servingQty = serving_qty,
        servingUnit = serving_unit,
        calories = nf_calories,
        photo = photo.thumb,
    )

fun ApiFood.asDomainFood(mealType: MealType): Food =
    Food(
        date = LocalDate.now(),
        meal = mealType,
        name = food_name,
        servingQty = serving_qty,
        servingUnit = serving_unit,
        calories = nf_calories,
        photo = photo.thumb,
    )
