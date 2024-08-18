package com.tieproost.fitnessapp.ui.meals.mutate

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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel class responsible for managing the state and actions of the meal mutate dialog.
 *
 * @property mealsRepository The repository for saving meals.
 */
class MealMutateViewModel(
    private val mealsRepository: MealsRepository,
) : ViewModel() {
    var apiState: MealsMutateApiState by mutableStateOf(MealsMutateApiState.Success)
        private set

    // cold flow
    private val _uiState = MutableStateFlow(MealMutateState())
    val uiState: StateFlow<MealMutateState> = _uiState.asStateFlow()

    fun updateQuery(query: String) {
        apiState = MealsMutateApiState.Success
        _uiState.update {
            it.copy(
                query = query,
            )
        }
    }

    fun search() {
        viewModelScope.launch {
            apiState = MealsMutateApiState.Loading
            mealsRepository
                .searchMeal(uiState.value.query)
                .catch {
                    apiState = MealsMutateApiState.Error
                }.collect {
                    _uiState.update { currentState ->
                        currentState.copy(
                            results = currentState.results + it.foods,
                        )
                    }
                    apiState = MealsMutateApiState.Success
                }
        }
    }

    fun addMeal(mealType: MealType) {
        viewModelScope.launch {
            _uiState.value.results.forEach {
                mealsRepository.insertFood(it, mealType)
            }
        }

        // reset the input fields
        _uiState.update { currentState ->
            currentState.copy(
                query = "",
                results = listOf(),
            )
        }
    }

    fun clearResults() {
        _uiState.update {
            it.copy(
                results = listOf(),
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val application = (
                        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as
                            FitnessApplication
                    )
                    val mealsRepository = application.container.mealsRepository
                    MealMutateViewModel(mealsRepository = mealsRepository)
                }
            }
    }
}
