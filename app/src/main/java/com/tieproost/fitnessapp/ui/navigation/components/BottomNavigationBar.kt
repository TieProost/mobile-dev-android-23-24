package com.tieproost.fitnessapp.ui.navigation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.tieproost.fitnessapp.ui.navigation.NavigationDestinations

/**
 * Composable function for bottom NavigationBar in compact mode.
 *
 * @param selectedDestination The current destination in navController.
 * @param navigateTo A function to navigate to [NavigationDestinations].
 */
@Composable
fun BottomNavigationBar(
    selectedDestination: String,
    navigateTo: (NavigationDestinations) -> Unit,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.onSecondary,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        NavigationDestinations.values().slice(0..2).forEach { destination ->
            val selected = selectedDestination == destination.name

            NavigationBarItem(
                icon = { Icon(destination.icon, destination.name) },
                label = { Text(stringResource(destination.textId)) },
                selected = selected,
                enabled = !selected,
                onClick = { navigateTo(destination) },
                modifier = Modifier.testTag(stringResource(destination.textId) + "NavigationItem"),
            )
        }
    }
}
