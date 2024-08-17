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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.tieproost.fitnessapp.R
import com.tieproost.fitnessapp.model.Exercise
import kotlin.math.roundToInt

@Composable
fun ExerciseListItem(exercise: Exercise) {
    ListItem(
        headlineContent = { Text(exercise.name) },
        supportingContent = { Text("${exercise.durationMinutes} ${stringResource(R.string.minutes_abrv)} ") },
        trailingContent = { Text("${exercise.calories.roundToInt()} ${stringResource(R.string.kcal)}") },
        leadingContent = {
            AsyncImage(
                model = exercise.photo,
                contentDescription = exercise.name + stringResource(R.string.photo),
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .width(dimensionResource(R.dimen.icon_large))
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(dimensionResource(R.dimen.rounding_small)))
                        .border(
                            dimensionResource(R.dimen.stroke_xs),
                            MaterialTheme.colorScheme.primaryContainer,
                            RoundedCornerShape(dimensionResource(R.dimen.rounding_small)),
                        ),
            )
        },
    )
}
