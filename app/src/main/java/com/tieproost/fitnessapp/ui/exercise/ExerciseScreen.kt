package com.tieproost.fitnessapp.ui.exercise

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tieproost.fitnessapp.ui.common.ErrorScreen
import com.tieproost.fitnessapp.ui.common.LoadingScreen
import com.tieproost.fitnessapp.ui.exercise.mutate.ExerciseMutateDialog
import com.tieproost.fitnessapp.ui.exercise.overview.ExerciseOverview
import com.tieproost.fitnessapp.ui.exercise.overview.ExerciseOverviewApiState
import com.tieproost.fitnessapp.ui.exercise.overview.ExerciseOverviewViewModel
import com.tieproost.fitnessapp.ui.navigation.NavigationDestinations

@Composable
fun ExerciseScreen() {
    val exerciseOverviewViewModel: ExerciseOverviewViewModel = viewModel(factory = ExerciseOverviewViewModel.Factory)
    val uiState by exerciseOverviewViewModel.uiState.collectAsState()
    val uiListState by exerciseOverviewViewModel.uiListState.collectAsState()
    val apiState = exerciseOverviewViewModel.apiState

    Box(
        modifier =
            Modifier
                .fillMaxHeight()
                .testTag(stringResource(NavigationDestinations.Exercise.textId)),
    ) {
        when (apiState) {
            is ExerciseOverviewApiState.Loading -> LoadingScreen()
            is ExerciseOverviewApiState.Error -> ErrorScreen()
            is ExerciseOverviewApiState.Success ->
                ExerciseOverview(
                    exercises = uiListState.exercises,
                    showDialog = exerciseOverviewViewModel::showDialog,
                )
        }

        if (uiState.isDialogVisible) {
            ExerciseMutateDialog(
                hideDialog = exerciseOverviewViewModel::hideDialog,
            )
        }
    }
}
