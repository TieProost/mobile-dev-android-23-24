package com.tieproost.fitnessapp.viewmodel

import com.tieproost.fitnessapp.data.database.model.MealType
import com.tieproost.fitnessapp.fake.repository.FakeMealsRepository
import com.tieproost.fitnessapp.helper.TestDispatcherRule
import com.tieproost.fitnessapp.ui.meals.overview.MealsOverviewViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MealsOverviewViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val newMealType = MealType.Lunch
    private lateinit var viewModel: MealsOverviewViewModel

    @Before
    fun initializeViewModel() {
        viewModel =
            MealsOverviewViewModel(
                mealsRepository = FakeMealsRepository(),
            )
    }

    @Test
    fun showDialogChangesState() {
        viewModel.showDialog(newMealType)

        assertTrue(viewModel.uiState.value.isDialogVisible)
        assertEquals(newMealType, viewModel.uiState.value.dialogMealType)
    }

    @Test
    fun hideDialogChangesState() {
        viewModel.showDialog(newMealType)
        assertTrue(viewModel.uiState.value.isDialogVisible)

        viewModel.hideDialog()
        assertFalse(viewModel.uiState.value.isDialogVisible)
    }
}
