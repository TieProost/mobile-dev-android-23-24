package com.tieproost.fitnessapp.ui.settings.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.tieproost.fitnessapp.R
import com.tieproost.fitnessapp.ui.navigation.NavigationDestinations

/**
 * Composable function for displaying generic settings editing dialog.
 *
 * @param dialogContent The Composable function with the setting to be edited.
 * @param hideDialog A lambda function to hide dialog.
 * @param saveSettings A lambda function to save settings.
 */
@Composable
fun SettingsDialog(
    dialogContent: @Composable () -> Unit,
    hideDialog: () -> Unit,
    saveSettings: () -> Unit,
) {
    Dialog(
        onDismissRequest = hideDialog,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Box(
            modifier =
                Modifier
                    .padding(dimensionResource(R.dimen.padding_medium))
                    .testTag(stringResource(NavigationDestinations.Settings.textId) + "AddDialog"),
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                dialogContent()

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = dimensionResource(R.dimen.padding_small),
                                end = dimensionResource(R.dimen.padding_small),
                                top = dimensionResource(R.dimen.padding_medium),
                            ),
                ) {
                    TextButton(
                        onClick = hideDialog,
                        modifier =
                            Modifier.testTag(
                                stringResource(NavigationDestinations.Settings.textId) +
                                    stringResource(R.string.cancel),
                            ),
                    ) {
                        Text(text = stringResource(R.string.cancel))
                    }
                    TextButton(onClick = {
                        saveSettings()
                        hideDialog()
                    }) {
                        Text(text = stringResource(R.string.ok))
                    }
                }
            }
        }
    }
}
