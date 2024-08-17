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
import androidx.compose.ui.res.dimensionResource
import com.tieproost.fitnessapp.R

@Composable
fun MutateDialogLoadingOverlay(
    isLoading: Boolean,
    content: @Composable () -> Unit,
) {
    Box {
        Box(
            modifier = Modifier.padding(dimensionResource(R.dimen.rounding_small)).padding(top = dimensionResource(R.dimen.padding_medium)),
        ) {
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
                Box(modifier = Modifier.padding(dimensionResource(R.dimen.padding_large))) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(dimensionResource(R.dimen.icon_xl)),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }
            }
        }
    }
}
