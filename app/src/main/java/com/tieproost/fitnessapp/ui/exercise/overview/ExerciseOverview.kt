package com.tieproost.fitnessapp.ui.exercise.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            text = "Total burned:",
            calories = exercises.sumOf { it.calories }.roundToInt(),
        )

        OverviewHeader(title = "Exercises", showDialog = showDialog)
        // Text("No Exercise logged yet.", modifier = Modifier)

        LazyColumn(state = rememberLazyListState(), modifier = Modifier.fillMaxHeight()) {
            items(exercises) {
                ExerciseListItem(exercise = it)
            }
        }
    }
}
