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

/**
 * Composable function that displays the destination of navigation.
 *
 * @param paddingValues Padding values passed by Scaffold.
 * @param navController [NavHostController] entity to manage navigating.
 * @param startDestination A lambda function to navigate to the car parks screen.
 */
@Composable
fun NavHostComponent(
    paddingValues: PaddingValues,
    navController: NavHostController,
    startDestination: String,
) {
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
            composable(route = destination.name) {
                destination.content()
            }
        }
    }
}
