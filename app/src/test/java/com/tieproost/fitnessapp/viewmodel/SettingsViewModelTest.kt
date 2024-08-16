package com.tieproost.fitnessapp.viewmodel

import com.tieproost.fitnessapp.fake.repository.FakeSettingsRepository
import com.tieproost.fitnessapp.helper.TestDispatcherRule
import com.tieproost.fitnessapp.ui.settings.SettingsViewModel
import com.tieproost.fitnessapp.ui.util.booleanToSexString
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SettingsViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private val newSex = false
    private val newHeight = 213
    private val newWeight = 123.31
    private val newCalorieGoal = 1234
    private lateinit var viewModel: SettingsViewModel

    @Before
    fun initializeViewModel() {
        viewModel =
            SettingsViewModel(
                settingsRepository = FakeSettingsRepository(),
            )
    }

    @Test
    fun showDialogChangesState() {
        viewModel.showDialog {}

        assertTrue(viewModel.uiState.value.isDialogVisible)
    }

    @Test
    fun hideDialogChangesState() {
        viewModel.showDialog {}
        assertTrue(viewModel.uiState.value.isDialogVisible)

        viewModel.hideDialog()
        assertFalse(viewModel.uiState.value.isDialogVisible)
    }

    @Test
    fun settingSexChangesState() {
        viewModel.onSexChange(booleanToSexString(newSex))
        assertEquals(newSex, viewModel.uiState.value.settingsForm.sex)
    }

    @Test
    fun settingHeightChangesState() {
        viewModel.onHeightChange(newHeight.toString())
        assertEquals(newHeight, viewModel.uiState.value.settingsForm.height)
    }

    @Test
    fun settingWeightChangesState() {
        viewModel.onWeightChange(newWeight.toString())
        assertEquals(newWeight, viewModel.uiState.value.settingsForm.weight)
    }

    @Test
    fun settingCalorieGoalChangesState() {
        viewModel.onCalorieGoalChange(newCalorieGoal.toString())
        assertEquals(newCalorieGoal, viewModel.uiState.value.settingsForm.calorieGoal)
    }
}
