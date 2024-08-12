package com.tieproost.fitnessapp.ui.meals.overview

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.tieproost.fitnessapp.data.database.model.MealType
import com.tieproost.fitnessapp.ui.meals.components.FoodListItem
import com.tieproost.fitnessapp.ui.meals.components.MealTypeHeader

@Composable
fun MealsOverview(
    uiListState: MealsListState,
    openDialog: (MealType) -> Unit,
) {
    LazyColumn(state = rememberLazyListState()) {
        items(MealType.values()) {
            MealTypeHeader(it, openDialog = { openDialog(it) })

            val meal = uiListState.foods.filter { food -> food.meal == it }
            if (meal.isEmpty()) {
                Text(text = "No foods logged")
            } else {
                meal.forEach { food -> FoodListItem(food) }
            }
        }
    }
}
