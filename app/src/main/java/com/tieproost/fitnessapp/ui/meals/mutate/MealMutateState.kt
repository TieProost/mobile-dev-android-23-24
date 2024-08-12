package com.tieproost.fitnessapp.ui.meals.mutate

import com.tieproost.fitnessapp.network.model.ApiFood

// the data class just holds the (immutable) values of the state
data class MealMutateState(
    val query: String = "",
    val results: List<ApiFood> = listOf(),
)

sealed interface MealsMutateApiState {
    object Success : MealsMutateApiState

    object Error : MealsMutateApiState

    object Loading : MealsMutateApiState
}
