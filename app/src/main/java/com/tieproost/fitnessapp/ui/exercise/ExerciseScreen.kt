package com.tieproost.fitnessapp.ui.exercise

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tieproost.fitnessapp.ui.common.ErrorScreen
import com.tieproost.fitnessapp.ui.common.LoadingScreen
import com.tieproost.fitnessapp.ui.exercise.mutate.ExerciseListItem
import com.tieproost.fitnessapp.ui.exercise.mutate.ExerciseMutateDialog
import com.tieproost.fitnessapp.ui.exercise.overview.ExerciseListState
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
                    uiListState = uiListState,
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

@Composable
fun ExerciseOverview(
    uiListState: ExerciseListState,
    showDialog: () -> Unit,
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                "Total calories burned:",
                modifier = Modifier.padding(vertical = 32.dp),
            )
            Card(
                colors =
                    CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    ),
                modifier = Modifier.padding(16.dp),
            ) {
                Text("523 kcal", modifier = Modifier.padding(16.dp), fontWeight = FontWeight.Bold)
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Text("Exercise", modifier = Modifier)
            Button(
                modifier = Modifier.testTag(stringResource(NavigationDestinations.Exercise.textId) + "AddButton"),
                onClick = showDialog,
            ) {
                Icon(Icons.Filled.Add, contentDescription = "")
            }
        }
        // Text("No Exercise logged yet.", modifier = Modifier)

        LazyColumn(state = rememberLazyListState(), modifier = Modifier.fillMaxHeight()) {
            items(uiListState.exercises) {
                ExerciseListItem(exercise = it)
            }
        }
    }
}
