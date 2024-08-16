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
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                Text(text = "Calories", style = MaterialTheme.typography.titleLarge)
                Text(text = "Remaining = Goal - Food + Exercise") // style = MaterialTheme.typography.titleSmall)
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
                        Text(text = "Remaining")
                    }
                    CircularProgressIndicator(
                        progress = { 1f },
                        modifier = Modifier.size(160.dp),
                        color = MaterialTheme.colorScheme.onSecondary,
                        strokeWidth = 16.dp,
                    )

                    CircularProgressIndicator(
                        progress = { progress },
                        modifier = Modifier.size(160.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        strokeWidth = 16.dp,
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Flag,
                            contentDescription = "todo",
                            modifier = Modifier.padding(12.dp),
                        )
                        Column {
                            Text("Base Goal")
                            Text(text = goal.toString())
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Restaurant,
                            contentDescription = "todo",
                            modifier = Modifier.padding(12.dp),
                        )
                        Column {
                            Text("Food")
                            Text(text = foodCalories.roundToInt().toString())
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.DirectionsRun,
                            contentDescription = "todo",
                            modifier = Modifier.padding(12.dp),
                        )
                        Column {
                            Text("Exercise")
                            Text(text = exerciseCalories.roundToInt().toString())
                        }
                    }
                }
            }
        }
    }
}
