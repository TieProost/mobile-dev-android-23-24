package com.tieproost.fitnessapp.ui.exercise.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tieproost.fitnessapp.model.Exercise
import kotlin.math.roundToInt

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
}
