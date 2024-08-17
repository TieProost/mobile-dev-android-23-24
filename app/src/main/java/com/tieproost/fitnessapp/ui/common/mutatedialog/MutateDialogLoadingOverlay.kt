package com.tieproost.fitnessapp.ui.common.mutatedialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MutateDialogLoadingOverlay(
    isLoading: Boolean,
    content: @Composable () -> Unit,
) {
    Box {
        Box(modifier = Modifier.padding(4.dp).padding(top = 16.dp)) {
            content()
        }

        if (isLoading) {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background.copy(alpha = 0.7f)),
            ) {
                Box(modifier = Modifier.padding(36.dp)) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }
            }
        }
    }
}
