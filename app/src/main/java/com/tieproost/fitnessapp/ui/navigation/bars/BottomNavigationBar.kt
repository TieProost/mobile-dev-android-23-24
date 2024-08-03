package com.tieproost.fitnessapp.ui.navigation.bars

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tieproost.fitnessapp.ui.navigation.NavigationDestinations

@Composable
fun BottomNavigationBar(
    selectedDestination: String,
    navigateTo: (NavigationDestinations) -> Unit,
) {
    NavigationBar {
        NavigationDestinations.values().forEach { destination ->
            val selected = selectedDestination == destination.name

            NavigationBarItem(
                icon = { Icon(destination.icon, destination.name) },
                label = { Text(stringResource(destination.textId)) },
                selected = selected,
                enabled = !selected,
                onClick = { navigateTo(destination) },
            )
        }
    }
}
