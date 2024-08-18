package com.tieproost.fitnessapp.ui.exercise.mutate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tieproost.fitnessapp.R
import com.tieproost.fitnessapp.data.database.model.asDomainExercise
import com.tieproost.fitnessapp.model.Exercise
import com.tieproost.fitnessapp.ui.common.mutatedialog.MutateDialogButtons
import com.tieproost.fitnessapp.ui.common.mutatedialog.MutateDialogLoadingOverlay
import com.tieproost.fitnessapp.ui.common.mutatedialog.MutateDialogTextField
import com.tieproost.fitnessapp.ui.common.mutatedialog.MutateDialogTopBar
import com.tieproost.fitnessapp.ui.exercise.components.ExerciseListItem
import com.tieproost.fitnessapp.ui.navigation.NavigationDestinations

/**
 * Composable function for displaying exercise add dialog.
 *
 * @param hideDialog A lambda function to hide dialog for adding.
 */
@Composable
fun ExerciseMutateDialog(hideDialog: () -> Unit) {
    val exerciseMutateViewModel: ExerciseMutateViewModel = viewModel(factory = ExerciseMutateViewModel.Factory)
    val uiState by exerciseMutateViewModel.uiState.collectAsState()
    val apiState = exerciseMutateViewModel.apiState

    Dialog(
        onDismissRequest = hideDialog,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Scaffold(
            modifier = Modifier.testTag(stringResource(NavigationDestinations.Exercise.textId) + "AddDialog"),
            topBar = {
                MutateDialogTopBar(
                    title = stringResource(R.string.add_exercise),
                    onBack = hideDialog,
                )
            },
        ) {
            Column(modifier = Modifier.padding(it)) {
                MutateDialogTextField(
                    value = uiState.query,
                    onValueChange = exerciseMutateViewModel::updateQuery,
                    placeholder = stringResource(R.string.add_exercise_placeholder),
                    isError = apiState == ExerciseMutateApiState.Error,
                )

                MutateDialogButtons(
                    onSearch = exerciseMutateViewModel::search,
                    onClear = exerciseMutateViewModel::clearResults,
                    onSave = {
                        exerciseMutateViewModel.addWorkout()
                        hideDialog()
                    },
                    isLoading = apiState == ExerciseMutateApiState.Loading,
                    isResultsEmpty = uiState.results.isEmpty(),
                )

                MutateDialogLoadingOverlay(isLoading = apiState == ExerciseMutateApiState.Loading) {
                    val lazyListState = rememberLazyListState()
                    LazyColumn(state = lazyListState) {
                        items(uiState.results) { it ->
                            ExerciseListItem(it.asDomainExercise())
                        }
                    }
                }
            }
        }
    }
}
