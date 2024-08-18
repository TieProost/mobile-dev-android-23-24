package com.tieproost.fitnessapp.ui.meals.mutate

import com.tieproost.fitnessapp.network.model.ApiFood

/**
* Represents the state of the meals mutate dialog.
*
* @property query The natural language query to search.
* @property results Results for the searched query.
*/
data class MealMutateState(
    val query: String = "",
    val results: List<ApiFood> = listOf(),
)

sealed interface MealsMutateApiState {
    object Success : MealsMutateApiState

    object Error : MealsMutateApiState

    object Loading : MealsMutateApiState
}
