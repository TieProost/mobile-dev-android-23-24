package com.tieproost.fitnessapp.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tieproost.fitnessapp.ui.navigation.NavHostComponent
import com.tieproost.fitnessapp.ui.navigation.NavigationActions
import com.tieproost.fitnessapp.ui.navigation.NavigationDestinations
import com.tieproost.fitnessapp.ui.navigation.bars.BottomNavigationBar
import com.tieproost.fitnessapp.ui.navigation.bars.TopNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitnessApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val startDestination = NavigationDestinations.Dashboard.name
    val selectedDestination = navBackStackEntry?.destination?.route ?: startDestination
    val navigationActions = remember(navController) { NavigationActions(navController) }

    Scaffold(
        topBar = {
            TopNavigationBar()
        },
        bottomBar = {
            BottomNavigationBar(
                selectedDestination = selectedDestination,
                navigateTo = navigationActions::navigateTo,
            )
        },
    ) {
        NavHostComponent(
            paddingValues = it,
            navController = navController,
            startDestination = startDestination,
        )
    }
}
