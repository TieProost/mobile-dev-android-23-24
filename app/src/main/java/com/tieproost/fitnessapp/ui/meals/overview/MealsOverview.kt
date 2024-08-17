package com.tieproost.fitnessapp.ui.meals.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tieproost.fitnessapp.R
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
            text = stringResource(R.string.total_eaten),
            calories = uiListState.foods.sumOf { it.calories }.roundToInt(),
        )

        LazyColumn(state = rememberLazyListState()) {
            items(MealType.values()) {
                OverviewHeader(title = it.name, showDialog = { showDialog(it) })

                val meal = uiListState.foods.filter { food -> food.meal == it }
                if (meal.isEmpty()) {
                    Text(
                        text = stringResource(R.string.no_foods_logged_yet),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp),
                    )
                } else {
                    meal.forEach { food -> FoodListItem(food) }
                }
            }
        }
    }
}
