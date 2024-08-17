package com.tieproost.fitnessapp.ui.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tieproost.fitnessapp.R
import kotlin.math.roundToInt

@Composable
fun CaloriesCard(
    goal: Int,
    foodCalories: Double,
    exerciseCalories: Double,
) {
    val total = goal + exerciseCalories.roundToInt()
    val remaining = total - foodCalories.roundToInt()
    val progress = foodCalories.toFloat() / total

    Card(
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onSecondary,
            ),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Column {
                Text(text = stringResource(R.string.calories), style = MaterialTheme.typography.titleLarge)
                Text(text = stringResource(R.string.remaining_goal_food_exercise)) // style = MaterialTheme.typography.titleSmall)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = remaining.toString(), style = MaterialTheme.typography.headlineMedium)
                        Text(text = stringResource(R.string.remaining))
                    }
                    CircularProgressIndicator(
                        progress = { 1f },
                        modifier = Modifier.size(160.dp),
                        color = MaterialTheme.colorScheme.surface,
                        strokeWidth = 20.dp,
                    )

                    CircularProgressIndicator(
                        progress = { progress },
                        modifier = Modifier.size(160.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        strokeWidth = 20.dp,
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    SettingItem(
                        icon = Icons.Filled.Flag,
                        title = stringResource(R.string.base_goal),
                        value = goal.toString(),
                    )

                    SettingItem(
                        icon = Icons.Filled.Restaurant,
                        title = stringResource(R.string.food),
                        value = foodCalories.roundToInt().toString(),
                    )

                    SettingItem(
                        icon = Icons.AutoMirrored.Filled.DirectionsRun,
                        title = stringResource(R.string.exercise),
                        value = exerciseCalories.roundToInt().toString(),
                    )
                }
            }
        }
    }
}
