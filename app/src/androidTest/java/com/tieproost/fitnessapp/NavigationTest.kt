package com.tieproost.fitnessapp

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.tieproost.fitnessapp.ui.FitnessApp
import com.tieproost.fitnessapp.ui.navigation.NavigationDestinations
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController
    private lateinit var context: Context

    private lateinit var dashboardText: String
    private lateinit var mealsText: String
    private lateinit var exerciseText: String
    private lateinit var settingsText: String

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            context = LocalContext.current
            navController = TestNavHostController(context)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            dashboardText = context.getString(NavigationDestinations.Dashboard.textId)
            mealsText = context.getString(NavigationDestinations.Meals.textId)
            exerciseText = context.getString(NavigationDestinations.Exercise.textId)
            settingsText = context.getString(NavigationDestinations.Settings.textId)

            FitnessApp(navController = navController)
        }
    }

    @Test
    fun verifyStartDestination() {
        composeTestRule
            .onNodeWithTag(dashboardText)
            .assertIsDisplayed()
    }

    @Test
    fun navigateToMealsAndOpenDialog() {
        composeTestRule.navigateToDestination(mealsText)
        composeTestRule.openDialog(mealsText)
    }

    @Test
    fun navigateToExerciseAndOpenDialog() {
        composeTestRule.navigateToDestination(exerciseText)
        composeTestRule.openDialog(exerciseText)
    }

    @Test
    fun navigateToSettingsAndOpenAllDialogs() {
        composeTestRule.navigateToDestination(settingsText)
        composeTestRule
            .onAllNodesWithTag(settingsText + "AddButton")
            .apply {
                fetchSemanticsNodes().forEachIndexed { i, _ ->
                    get(i)
                        .assertIsDisplayed()
                        .performClick()

                    composeTestRule
                        .onNodeWithTag(settingsText + "AddDialog")
                        .assertIsDisplayed()

                    composeTestRule
                        .onNodeWithTag(settingsText + "Cancel")
                        .assertIsDisplayed()
                        .performClick()

                    composeTestRule
                        .onNodeWithTag(settingsText + "AddDialog")
                        .assertDoesNotExist()
                }
            }
    }

    private fun ComposeContentTestRule.navigateToDestination(name: String) {
        this
            .onNodeWithTag(name + "NavigationItem")
            .assertIsDisplayed()
            .performClick()
        this
            .onNodeWithTag(name)
            .assertIsDisplayed()
    }

    private fun ComposeContentTestRule.openDialog(name: String) {
        this
            .onAllNodesWithTag(name + "AddButton")[0]
            .assertIsDisplayed()
            .performClick()
        this
            .onNodeWithTag(name + "AddDialog")
            .assertIsDisplayed()
    }
}
