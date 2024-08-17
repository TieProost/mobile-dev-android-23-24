package com.tieproost.fitnessapp.ui.exercise.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tieproost.fitnessapp.ui.common.OverviewHeader
import com.tieproost.fitnessapp.ui.exercise.components.ExerciseListItem

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
        OverviewHeader(title = "Exercises", showDialog = showDialog)
        // Text("No Exercise logged yet.", modifier = Modifier)

        LazyColumn(state = rememberLazyListState(), modifier = Modifier.fillMaxHeight()) {
            items(uiListState.exercises) {
                ExerciseListItem(exercise = it)
            }
        }
    }
}
