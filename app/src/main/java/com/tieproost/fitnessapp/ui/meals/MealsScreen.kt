package com.tieproost.fitnessapp.ui.meals

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tieproost.fitnessapp.ui.meals.mutate.MealMutateDialog
import com.tieproost.fitnessapp.ui.meals.overview.MealsOverview
import com.tieproost.fitnessapp.ui.meals.overview.MealsOverviewApiState
import com.tieproost.fitnessapp.ui.meals.overview.MealsOverviewViewModel

@Composable
fun MealsScreen() {
    val mealsOverviewViewModel: MealsOverviewViewModel = viewModel(factory = MealsOverviewViewModel.Factory)
    val uiState by mealsOverviewViewModel.uiState.collectAsState()
    val uiListState by mealsOverviewViewModel.uiListState.collectAsState()
    val apiState = mealsOverviewViewModel.apiState

    Box(modifier = Modifier.fillMaxHeight()) {
        when (apiState) {
            is MealsOverviewApiState.Loading -> Text("Loading...")
            is MealsOverviewApiState.Error -> Text("Couldn't load...")
            is MealsOverviewApiState.Success ->
                MealsOverview(
                    uiListState = uiListState,
                    showDialog = mealsOverviewViewModel::showDialog,
                )
        }

        if (uiState.isDialogVisible) {
            MealMutateDialog(
                hideDialog = mealsOverviewViewModel::hideDialog,
                mealType = uiState.dialogMealType,
            )
        }
    }
}
