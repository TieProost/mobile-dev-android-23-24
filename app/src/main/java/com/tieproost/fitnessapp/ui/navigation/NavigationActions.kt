package com.tieproost.fitnessapp.ui.navigation

import androidx.navigation.NavHostController

class NavigationActions(
    private val navController: NavHostController,
) {
    fun navigateTo(destination: NavigationDestinations) {
        navController.navigate(destination.name) {
            // Pop up to the last identical destination to avoid duplicates
            popUpTo(navController.graph.findLast { it.route == destination.name }!!.id) {
                saveState = false
            }
            // Avoid re-selecting the same item
            launchSingleTop = true
            // Restore state when selecting a previously selected item
            restoreState = true
        }
    }
}
