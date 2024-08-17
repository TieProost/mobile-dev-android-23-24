package com.tieproost.fitnessapp.ui.settings.dialog.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.tieproost.fitnessapp.R

@Composable
fun DialogColumn(
    title: String,
    content: @Composable () -> Unit,
) {
    Column(
        modifier =
            Modifier.padding(
                horizontal = dimensionResource(R.dimen.spacing_medium),
                vertical = dimensionResource(R.dimen.padding_medium),
            ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_large)),
    ) {
        Text(text = title, style = MaterialTheme.typography.titleSmall)
        content()
    }
}
