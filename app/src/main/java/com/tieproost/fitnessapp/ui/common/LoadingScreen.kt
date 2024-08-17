package com.tieproost.fitnessapp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            Modifier.fillMaxSize().padding(36.dp).background(MaterialTheme.colorScheme.background.copy(alpha = 0.7f)),
    ) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxWidth(0.25f),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}
