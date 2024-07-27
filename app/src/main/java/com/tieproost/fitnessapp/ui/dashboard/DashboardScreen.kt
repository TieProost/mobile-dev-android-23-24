package com.tieproost.fitnessapp.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen() {
    Column(
        modifier =
            Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        FilledCard(
            Modifier.fillMaxWidth().weight(1f),
        )
        Row(Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            FilledCard(
                Modifier.weight(1f).fillMaxHeight(),
            )
            FilledCard(
                Modifier.weight(1f).fillMaxHeight(),
            )
        }
        FilledCard(
            Modifier.fillMaxWidth().weight(1f),
        )
    }
}

@Composable
private fun FilledCard(modifier: Modifier) {
    Card(
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
        modifier = modifier,
    ) {
//        CircularProgressIndicator(
//            0.9f,
//            modifier = Modifier.fillMaxHeight(),
//        )
    }
}
