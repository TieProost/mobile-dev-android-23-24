package com.tieproost.fitnessapp.ui.common.mutatedialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.tieproost.fitnessapp.R

@Composable
fun MutateDialogButtons(
    onSearch: () -> Unit,
    onClear: () -> Unit,
    onSave: () -> Unit,
    isLoading: Boolean,
    isResultsEmpty: Boolean,
) {
    Row(
        modifier =
            Modifier
                .padding(horizontal = dimensionResource(R.dimen.padding_large))
                .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_hor_medium)),
    ) {
        Button(
            onClick = onSearch,
            enabled = !isLoading,
        ) {
            Icon(Icons.Filled.Search, contentDescription = stringResource(R.string.search))
            Text(text = stringResource(R.string.add).replaceFirstChar { it.lowercase() })
        }

        Button(
            onClick = onClear,
            enabled = !isLoading && !isResultsEmpty,
        ) {
            Icon(Icons.Filled.Refresh, contentDescription = stringResource(R.string.clear_list))
            Text(text = stringResource(R.string.clear))
        }

        Button(
            onClick = onSave,
            enabled = !isLoading && !isResultsEmpty,
        ) {
            Icon(Icons.Filled.Save, contentDescription = stringResource(R.string.save))
            Text(text = stringResource(R.string.save).replaceFirstChar { it.lowercase() })
        }
    }
}
