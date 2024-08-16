package com.tieproost.fitnessapp.viewmodel

import com.tieproost.fitnessapp.fake.repository.FakeExerciseRepository
import com.tieproost.fitnessapp.helper.TestDispatcherRule
import com.tieproost.fitnessapp.ui.exercise.overview.ExerciseOverviewViewModel
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExerciseOverviewViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var viewModel: ExerciseOverviewViewModel

    @Before
    fun initializeViewModel() {
        viewModel =
            ExerciseOverviewViewModel(
                exerciseRepository = FakeExerciseRepository(),
            )
    }

    @Test
    fun showDialogChangesState() {
        viewModel.showDialog()
        assertTrue(viewModel.uiState.value.isDialogVisible)
    }

    @Test
    fun hideDialogChangesState() {
        viewModel.showDialog()
        assertTrue(viewModel.uiState.value.isDialogVisible)

        viewModel.hideDialog()
        assertFalse(viewModel.uiState.value.isDialogVisible)
    }
}
