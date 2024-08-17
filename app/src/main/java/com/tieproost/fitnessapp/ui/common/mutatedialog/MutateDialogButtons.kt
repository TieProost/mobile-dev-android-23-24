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
import androidx.compose.ui.unit.dp

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
                .padding(horizontal = 32.dp)
                .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Button(
            onClick = onSearch,
            enabled = !isLoading,
        ) {
            Icon(Icons.Filled.Search, contentDescription = "")
            Text(text = " add ")
        }

        Button(
            onClick = onClear,
            enabled = !isLoading && !isResultsEmpty,
        ) {
            Icon(Icons.Filled.Refresh, contentDescription = " save")
            Text(text = " clear")
        }

        Button(
            onClick = onSave,
            enabled = !isLoading && !isResultsEmpty,
        ) {
            Icon(Icons.Filled.Save, contentDescription = "Save")
            Text(text = " save")
        }
    }
}
