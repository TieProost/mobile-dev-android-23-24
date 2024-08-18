package com.tieproost.fitnessapp.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tieproost.fitnessapp.ui.navigation.NavHostComponent
import com.tieproost.fitnessapp.ui.navigation.NavigationActions
import com.tieproost.fitnessapp.ui.navigation.NavigationDestinations
import com.tieproost.fitnessapp.ui.navigation.components.BottomNavigationBar
import com.tieproost.fitnessapp.ui.navigation.components.NavigationDrawerContent
import com.tieproost.fitnessapp.ui.navigation.components.TopNavigationBar
import com.tieproost.fitnessapp.ui.util.NavigationType

/**
 * Entry point for the Android app.
 *
 * @param navController The navigation controller for managing app navigation.
 * @param navigationType Type of navigation based on screen size.
 */
@Composable
fun FitnessApp(
    navController: NavHostController = rememberNavController(),
    navigationType: NavigationType = NavigationType.BOTTOM_NAVIGATION,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val startDestination = NavigationDestinations.Dashboard.name
    val selectedDestination = navBackStackEntry?.destination?.route ?: startDestination
    val navigationActions = remember(navController) { NavigationActions(navController) }

    if (navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(drawerContent = {
            NavigationDrawerContent(
                selectedDestination = selectedDestination,
                navigateTo = navigationActions::navigateTo,
            )
        }) {
            NavHostComponent(
                paddingValues = PaddingValues(),
                navController = navController,
                startDestination = startDestination,
            )
        }
    } else { // if (navigationType == NavigationType.BOTTOM_NAVIGATION) {
        Scaffold(
            topBar = {
                TopNavigationBar(
                    navigateToSettings = { navigationActions.navigateTo(NavigationDestinations.Settings) },
                    selectedDestination = selectedDestination,
                )
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
}
