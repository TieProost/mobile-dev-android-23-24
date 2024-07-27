package com.tieproost.fitnessapp.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavHostComponent(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    val startDestination = NavigationDestinations.Dashboard.name
    val direction = if (navController.currentDestination?.route == startDestination) -1 else 1
    val duration = 300

    NavHost(
        enterTransition = { slideInHorizontally(tween(duration)) { it * direction } },
        exitTransition = { slideOutHorizontally(tween(duration)) { -it * direction } },
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = startDestination,
    ) {
        NavigationDestinations.values().forEach { destination ->
            composable(route = destination.name) { destination.content() }
        }
    }
}
