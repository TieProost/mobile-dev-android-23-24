package com.tieproost.fitnessapp.ui.meals.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tieproost.fitnessapp.data.database.model.MealType
import com.tieproost.fitnessapp.ui.common.OverviewHeader
import com.tieproost.fitnessapp.ui.common.TotalCaloriesHeader
import com.tieproost.fitnessapp.ui.meals.components.FoodListItem
import kotlin.math.roundToInt

@Composable
fun MealsOverview(
    uiListState: MealsListState,
    showDialog: (MealType) -> Unit,
) {
    Column {
        TotalCaloriesHeader(
            text = "Total eaten:",
            calories = uiListState.foods.sumOf { it.calories }.roundToInt(),
        )

        LazyColumn(state = rememberLazyListState()) {
            items(MealType.values()) {
                OverviewHeader(title = it.name, showDialog = { showDialog(it) })

                val meal = uiListState.foods.filter { food -> food.meal == it }
                if (meal.isEmpty()) {
                    Text(
                        text = "No foods logged yet",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp),
                    )
                } else {
                    meal.forEach { food -> FoodListItem(food) }
                }
            }
        }
    }
}


