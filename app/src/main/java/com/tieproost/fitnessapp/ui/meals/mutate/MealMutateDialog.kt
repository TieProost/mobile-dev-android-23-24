package com.tieproost.fitnessapp.ui.meals.mutate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tieproost.fitnessapp.R
import com.tieproost.fitnessapp.data.database.model.MealType
import com.tieproost.fitnessapp.data.database.model.asDomainFood
import com.tieproost.fitnessapp.ui.common.mutatedialog.MutateDialogButtons
import com.tieproost.fitnessapp.ui.common.mutatedialog.MutateDialogLoadingOverlay
import com.tieproost.fitnessapp.ui.common.mutatedialog.MutateDialogTextField
import com.tieproost.fitnessapp.ui.common.mutatedialog.MutateDialogTopBar
import com.tieproost.fitnessapp.ui.meals.components.FoodListItem
import com.tieproost.fitnessapp.ui.navigation.NavigationDestinations

@Composable
fun MealMutateDialog(
    hideDialog: () -> Unit,
    mealType: MealType,
) {
    val mealMutateViewModel: MealMutateViewModel = viewModel(factory = MealMutateViewModel.Factory)
    val uiState by mealMutateViewModel.uiState.collectAsState()
    val apiState = mealMutateViewModel.apiState

    Dialog(
        onDismissRequest = hideDialog,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Scaffold(
            modifier = Modifier.testTag(stringResource(NavigationDestinations.Meals.textId) + "AddDialog"),
            topBar = {
                MutateDialogTopBar(
                    title = stringResource(R.string.add) + " " + mealType.name,
                    onBack = hideDialog,
                )
            },
        ) {
            Column(modifier = Modifier.padding(it)) {
                MutateDialogTextField(
                    value = uiState.query,
                    onValueChange = mealMutateViewModel::updateQuery,
                    placeholder = stringResource(R.string.add_meal_placeholder),
                    isError = apiState == MealsMutateApiState.Error,
                )

                MutateDialogButtons(
                    onSearch = mealMutateViewModel::search,
                    onClear = mealMutateViewModel::clearResults,
                    onSave = {
                        mealMutateViewModel.addMeal(mealType)
                        hideDialog()
                    },
                    isLoading = apiState == MealsMutateApiState.Loading,
                    isResultsEmpty = uiState.results.isEmpty(),
                )

                MutateDialogLoadingOverlay(apiState == MealsMutateApiState.Loading) {
                    LazyColumn(state = rememberLazyListState()) {
                        items(uiState.results) { it ->
                            FoodListItem(it.asDomainFood(mealType))
                        }
                    }
                }
            }
        }
    }
}
