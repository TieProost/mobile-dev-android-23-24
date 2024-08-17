package com.tieproost.fitnessapp.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.tieproost.fitnessapp.R

@Composable
fun OverviewHeader(
    title: String,
    showDialog: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
                .padding(top = dimensionResource(R.dimen.padding_small)),
    ) {
        Text(text = "$title:", style = MaterialTheme.typography.titleMedium)
        IconButton(
            modifier = Modifier.testTag("AddButton"),
            onClick = showDialog,
        ) {
            Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.add_button))
        }
    }
    HorizontalDivider()
}
