package com.tieproost.fitnessapp.ui.meals

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tieproost.fitnessapp.ui.common.ErrorScreen
import com.tieproost.fitnessapp.ui.common.LoadingScreen
import com.tieproost.fitnessapp.ui.meals.mutate.MealMutateDialog
import com.tieproost.fitnessapp.ui.meals.overview.MealsOverview
import com.tieproost.fitnessapp.ui.meals.overview.MealsOverviewApiState
import com.tieproost.fitnessapp.ui.meals.overview.MealsOverviewViewModel
import com.tieproost.fitnessapp.ui.navigation.NavigationDestinations

@Composable
fun MealsScreen() {
    val mealsOverviewViewModel: MealsOverviewViewModel = viewModel(factory = MealsOverviewViewModel.Factory)
    val uiState by mealsOverviewViewModel.uiState.collectAsState()
    val uiListState by mealsOverviewViewModel.uiListState.collectAsState()
    val apiState = mealsOverviewViewModel.apiState

    Box(
        modifier =
        Modifier
            .fillMaxHeight()
            .testTag(stringResource(NavigationDestinations.Meals.textId)),
    ) {
        when (apiState) {
            is MealsOverviewApiState.Loading -> LoadingScreen()
            is MealsOverviewApiState.Error -> ErrorScreen()
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
