package com.tieproost.fitnessapp.ui.exercise.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.tieproost.fitnessapp.model.Exercise
import com.tieproost.fitnessapp.ui.common.OverviewHeader
import com.tieproost.fitnessapp.ui.common.TotalCaloriesHeader
import com.tieproost.fitnessapp.ui.exercise.components.ExerciseListItem
import kotlin.math.roundToInt

@Composable
fun ExerciseOverview(
    exercises: List<Exercise>,
    showDialog: () -> Unit,
) {
    Column {
        TotalCaloriesHeader(
            text = stringResource(R.string.total_burned),
            calories = exercises.sumOf { it.calories }.roundToInt(),
        )

        OverviewHeader(title = stringResource(R.string.exercises), showDialog = showDialog)

        if (exercises.isEmpty()) {
            Text(
                text = stringResource(R.string.no_exercises_logged_yet),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp),
            )
        } else {
            LazyColumn(state = rememberLazyListState(), modifier = Modifier.fillMaxHeight()) {
                items(exercises) {
                    ExerciseListItem(exercise = it)
                }
            }
        }
    }
}
