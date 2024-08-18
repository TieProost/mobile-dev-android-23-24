package com.tieproost.fitnessapp.ui.settings.dialog.dialogcontent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tieproost.fitnessapp.R
import com.tieproost.fitnessapp.ui.settings.dialog.components.DialogColumn

/**
 * Composable function for displaying sex setting dialog content.
 *
 * @param onValueChange Function to change the value.
 * @param value Current value.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SexDialogContent(
    onValueChange: (String) -> Unit,
    value: Boolean?,
) {
    val options =
        listOf(
            stringResource(R.string.n_a),
            stringResource(R.string.male),
            stringResource(R.string.female),
        )
    var expanded by remember { mutableStateOf(false) }

    DialogColumn(title = stringResource(R.string.select_sex)) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
        ) {
            OutlinedTextField(
                readOnly = true,
                value =
                    when (value) {
                        true -> options[1]
                        false -> options[2]
                        else -> {
                            options[0]
                        }
                    },
                onValueChange = { },
                label = { Text(stringResource(R.string.sex)) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded,
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier =
                    Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            onValueChange(selectionOption)
                            expanded = false
                        },
                        text = { Text(text = selectionOption) },
                    )
                }
            }
        }
    }
}
