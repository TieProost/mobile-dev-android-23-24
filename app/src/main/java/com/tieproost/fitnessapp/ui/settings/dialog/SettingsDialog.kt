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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.tieproost.fitnessapp.ui.navigation.NavigationDestinations

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
                    .padding(16.dp)
                    .testTag(stringResource(NavigationDestinations.Settings.textId) + "AddDialog"),
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                dialogContent()

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp, end = 8.dp, top = 16.dp),
                ) {
                    TextButton(
                        onClick = hideDialog,
                        modifier =
                            Modifier.testTag(
                                stringResource(NavigationDestinations.Settings.textId) + "Cancel",
                            ),
                    ) {
                        Text(text = "Cancel")
                    }
                    TextButton(onClick = {
                        saveSettings()
                        hideDialog()
                    }) {
                        Text(text = "OK")
                    }
                }
            }
        }
    }
}
