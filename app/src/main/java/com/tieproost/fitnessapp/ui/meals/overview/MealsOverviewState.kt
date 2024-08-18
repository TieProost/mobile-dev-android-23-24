package com.tieproost.fitnessapp.ui.meals.overview

import com.tieproost.fitnessapp.data.database.model.MealType
import com.tieproost.fitnessapp.model.Food

/**
 * Represents the state of the meals overview screen.
 *
 * @property isDialogVisible Whether the dialog for adding foods is visible.
 * @property dialogMealType Type of meal to be added in dialog.
 */
data class MealsOverviewState(
    val isDialogVisible: Boolean = false,
    val dialogMealType: MealType = MealType.Breakfast,
)

data class MealsListState(
    val foods: List<Food> = listOf(),
)

sealed interface MealsOverviewApiState {
    object Success : MealsOverviewApiState

    object Error : MealsOverviewApiState

    object Loading : MealsOverviewApiState
}
