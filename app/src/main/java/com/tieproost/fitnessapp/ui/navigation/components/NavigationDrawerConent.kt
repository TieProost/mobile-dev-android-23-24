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
import androidx.compose.ui.unit.dp
import com.tieproost.fitnessapp.ui.navigation.NavigationDestinations

@Composable
fun NavigationDrawerContent(
    selectedDestination: String,
    navigateTo: (NavigationDestinations) -> Unit,
) {
    PermanentDrawerSheet(Modifier.width(200.dp).padding(16.dp)) {
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
