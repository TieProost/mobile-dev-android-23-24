package com.tieproost.fitnessapp.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

class NavigationActions(
    private val navController: NavHostController,
) {
    fun navigateTo(destination: NavigationDestinations) {
        navController.navigate(destination.name) {
            // Pop up to the start destination of the graph to avoid building up a large stack of destinations
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid re-selecting the same item
            launchSingleTop = true
            // Restore state when selecting a previously selected item
            restoreState = true
        }
    }
}
