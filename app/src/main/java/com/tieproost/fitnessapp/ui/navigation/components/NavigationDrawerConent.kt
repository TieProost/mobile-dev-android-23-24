package com.tieproost.fitnessapp.ui.navigation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.tieproost.fitnessapp.R
import com.tieproost.fitnessapp.ui.navigation.NavigationDestinations

/**
 * Composable function for Drawer navigation.
 *
 * @param selectedDestination The current destination in navController.
 * @param navigateTo A function to navigate to [NavigationDestinations].
 */
@Composable
fun NavigationDrawerContent(
    selectedDestination: String,
    navigateTo: (NavigationDestinations) -> Unit,
) {
    PermanentDrawerSheet(
        Modifier
            .width(dimensionResource(R.dimen.drawer_large))
            .padding(dimensionResource(R.dimen.padding_medium)),
    ) {
        Column(modifier = Modifier) {
            for (destination in NavigationDestinations.values()) {
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = destination.name,
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = destination.icon,
                            contentDescription = destination.name,
                        )
                    },
                    onClick = { navigateTo(destination) },
                    selected = destination.name == selectedDestination,
                )
            }
        }
    }
}
