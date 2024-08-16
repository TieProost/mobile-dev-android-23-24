package com.tieproost.fitnessapp.viewmodel

import com.tieproost.fitnessapp.fake.FakeDataSource
import com.tieproost.fitnessapp.fake.repository.FakeExerciseRepository
import com.tieproost.fitnessapp.fake.repository.FakeMealsRepository
import com.tieproost.fitnessapp.fake.repository.FakeSettingsRepository
import com.tieproost.fitnessapp.helper.TestDispatcherRule
import com.tieproost.fitnessapp.ui.dashboard.DashboardViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class DashboardViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun initialSettings() =
        runTest {
            val viewModel =
                DashboardViewModel(
                    settingsRepository = FakeSettingsRepository(),
                    mealsRepository = FakeMealsRepository(),
                    exerciseRepository = FakeExerciseRepository(),
                )

            backgroundScope.launch(UnconfinedTestDispatcher()) {
                viewModel.uiState.collect()
            }

            assertEquals(FakeDataSource.totalFoodCalories, viewModel.uiState.value.foodCalories)
            assertEquals(FakeDataSource.totalExerciseCalories, viewModel.uiState.value.exerciseCalories)
            assertEquals(FakeDataSource.settings, viewModel.uiState.value.settings)
        }
}
