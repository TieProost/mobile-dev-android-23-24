package com.tieproost.fitnessapp.viewmodel

import com.tieproost.fitnessapp.fake.repository.FakeExerciseRepository
import com.tieproost.fitnessapp.helper.TestDispatcherRule
import com.tieproost.fitnessapp.network.model.ApiExercise
import com.tieproost.fitnessapp.ui.exercise.mutate.ExerciseMutateViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExerciseMutateViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val newQuery = "run 1 hour"
    private lateinit var viewModel: ExerciseMutateViewModel

    @Before
    fun initializeViewModel() {
        viewModel =
            ExerciseMutateViewModel(
                exerciseRepository = FakeExerciseRepository(),
            )
    }

    @Test
    fun updateQueryChangesState() {
        viewModel.updateQuery(newQuery)

        assertEquals(newQuery, viewModel.uiState.value.query)
    }

    @Test
    fun addWorkoutResetsState() {
        viewModel.updateQuery(newQuery)
        viewModel.addWorkout()

        assertEquals("", viewModel.uiState.value.query)
        assertEquals(listOf<ApiExercise>(), viewModel.uiState.value.results)
    }
}
