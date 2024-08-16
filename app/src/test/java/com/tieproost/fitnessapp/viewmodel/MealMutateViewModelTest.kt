package com.tieproost.fitnessapp.viewmodel

import com.tieproost.fitnessapp.data.database.model.MealType
import com.tieproost.fitnessapp.fake.repository.FakeMealsRepository
import com.tieproost.fitnessapp.helper.TestDispatcherRule
import com.tieproost.fitnessapp.network.model.ApiFood
import com.tieproost.fitnessapp.ui.meals.mutate.MealMutateViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MealMutateViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val newMealType = MealType.Lunch
    private val newQuery = "2 apples"
    private lateinit var viewModel: MealMutateViewModel

    @Before
    fun initializeViewModel() {
        viewModel =
            MealMutateViewModel(
                mealsRepository = FakeMealsRepository(),
            )
    }

    @Test
    fun updateQueryChangesState() {
        viewModel.updateQuery(newQuery)

        assertEquals(newQuery, viewModel.uiState.value.query)
    }

    @Test
    fun addMealResetsState() {
        viewModel.updateQuery(newQuery)
        viewModel.addMeal(newMealType)

        assertEquals("", viewModel.uiState.value.query)
        assertEquals(listOf<ApiFood>(), viewModel.uiState.value.results)
    }
}
