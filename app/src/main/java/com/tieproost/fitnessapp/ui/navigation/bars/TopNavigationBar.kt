package com.tieproost.fitnessapp.ui.navigation.bars

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tieproost.fitnessapp.R
import com.tieproost.fitnessapp.ui.navigation.NavigationDestinations

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopNavigationBar(
    navigateToSettings: () -> Unit,
    selectedDestination: String,
) {
    val selected = selectedDestination == NavigationDestinations.Settings.name

    CenterAlignedTopAppBar(
        colors =
            TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.onSecondary,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.SportsGymnastics,
                    contentDescription = stringResource(R.string.app_logo),
                    modifier = Modifier.size(40.dp),
                )
                Text(stringResource(R.string.app_name))
            }
        },
        actions = {
            Row(modifier = Modifier.fillMaxWidth(0.25f)) {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Settings, Icons.Filled.Settings.name, Modifier) },
                    selected = selected,
                    enabled = !selected,
                    onClick = navigateToSettings,
                    modifier = Modifier.testTag(stringResource(NavigationDestinations.Settings.textId) +"NavigationItem"),

                )
            }
        },
    )
}
