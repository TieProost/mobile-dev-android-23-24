package com.tieproost.fitnessapp.ui.exercise.mutate

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.tieproost.fitnessapp.data.database.model.asDomainExercise
import com.tieproost.fitnessapp.model.Exercise
import com.tieproost.fitnessapp.ui.navigation.NavigationDestinations
import kotlin.math.roundToInt

@Composable
@OptIn(ExperimentalMaterial3Api::class)
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
                TopAppBar(title = { Text("Add exercise") }, navigationIcon = {
                    IconButton(onClick = hideDialog) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Menu back")
                    }
                })
            },
        ) {
            Column(modifier = Modifier.padding(it)) {
                TextField(
                    value = uiState.query,
                    onValueChange = exerciseMutateViewModel::updateQuery,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "placeholder") },
                )

                Button(onClick = exerciseMutateViewModel::search, enabled = apiState != ExerciseMutateApiState.Loading) {
                    Text(text = "Search")
                    Icon(Icons.Filled.Search, contentDescription = "")
                }

                val lazyListState = rememberLazyListState()
                LazyColumn(state = lazyListState, modifier = Modifier.padding(4.dp)) {
                    items(uiState.results) { it ->
                        ExerciseListItem(it.asDomainExercise())
                    }
                }

                Button(onClick = {
                    exerciseMutateViewModel.addWorkout()
                    hideDialog()
                }, enabled = apiState == ExerciseMutateApiState.Success && uiState.results.isNotEmpty()) {
                    Text(text = "Save")
                    Icon(Icons.Filled.Save, contentDescription = "Save")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseListItem(exercise: Exercise) {
    ListItem(
        headlineContent = { Text(exercise.name) },
        supportingContent = { Text("${exercise.durationMinutes} min.") },
        trailingContent = { Text("${exercise.calories.roundToInt()} kcal") },
        leadingContent = {
            AsyncImage(
                model = exercise.photo,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .width(40.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(4.dp))
                        .border(
                            0.5.dp,
                            MaterialTheme.colorScheme.primaryContainer,
                            RoundedCornerShape(
                                4.dp,
                            ),
                        ),
            )
        },
    )
    HorizontalDivider()
}
