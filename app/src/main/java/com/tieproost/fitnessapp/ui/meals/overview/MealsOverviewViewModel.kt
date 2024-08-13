package com.tieproost.fitnessapp.ui.meals.overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.tieproost.fitnessapp.FitnessApplication
import com.tieproost.fitnessapp.data.database.model.MealType
import com.tieproost.fitnessapp.data.repository.MealsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.io.IOException

class MealsOverviewViewModel(
    private val mealsRepository: MealsRepository,
) : ViewModel() {
    var apiState: MealsOverviewApiState by mutableStateOf(MealsOverviewApiState.Loading)
        private set

    // uiState is a cold flow. Changes don't come in from above unless a refresh is called
    private val _uiState = MutableStateFlow(MealsOverviewState())
    val uiState: StateFlow<MealsOverviewState> = _uiState.asStateFlow()

    // uiListState is a hot flow (.stateIn makes it so)
    lateinit var uiListState: StateFlow<MealsListState>

    init {
        getMeals()
    }

    private fun getMeals() {
        try {
            uiListState =
                mealsRepository
                    .getMeals()
                    .map { MealsListState(it) }
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5_000L),
                        initialValue = MealsListState(),
                    )

            apiState = MealsOverviewApiState.Success
        } catch (e: IOException) {
            apiState = MealsOverviewApiState.Error
        }
    }

    fun hideDialog() {
        _uiState.update {
            it.copy(isDialogVisible = false)
        }
    }

    fun showDialog(dialogMealType: MealType) {
        _uiState.update {
            it.copy(
                dialogMealType = dialogMealType,
                isDialogVisible = true,
            )
        }
    }

    // object to tell the android framework how to handle the parameter of the viewModel
    companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val application =
                        (
                            this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as
                                FitnessApplication
                        )
                    val mealsRepository = application.container.mealsRepository
                    MealsOverviewViewModel(mealsRepository = mealsRepository)
                }
            }
    }
}
