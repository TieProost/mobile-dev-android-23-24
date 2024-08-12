package com.tieproost.fitnessapp.ui.meals.mutate

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.tieproost.fitnessapp.data.database.MealType
import com.tieproost.fitnessapp.network.model.ApiFood
import kotlin.math.roundToInt

@Composable
@OptIn(ExperimentalMaterial3Api::class)
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
            topBar = {
                TopAppBar(title = { Text("Add ${mealType.name}") }, navigationIcon = {
                    IconButton(onClick = hideDialog) {
                        Icon(Icons.Filled.ArrowBack, "Menu back")
                    }
                })
            },
        ) {
            Column(modifier = Modifier.padding(it)) {
                TextField(
                    value = uiState.query,
                    onValueChange = mealMutateViewModel::updateQuery,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "placeholder") },
                )

                Button(onClick = mealMutateViewModel::search, enabled = apiState != MealsMutateApiState.Loading) {
                    Text(text = "Search")
                    Icon(Icons.Filled.Search, contentDescription = "")
                }

                val lazyListState = rememberLazyListState()
                LazyColumn(state = lazyListState, modifier = Modifier.padding(4.dp)) {
                    items(uiState.results) { it ->
                        MealListItem(it)
                    }
                }

                Button(onClick = {
                    mealMutateViewModel.addMeal(mealType)
                    hideDialog()
                }, enabled = apiState == MealsMutateApiState.Success && uiState.results.isNotEmpty()) {
                    Text(text = "Save")
                    Icon(Icons.Filled.Save, contentDescription = "Save")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealListItem(food: ApiFood) {
    ListItem(
        headlineText = { Text(food.food_name) },
        supportingText = { Text("${food.serving_qty} x ${food.serving_unit}") },
        trailingContent = { Text("${food.nf_calories.roundToInt()} kcal") },
        leadingContent = {
            AsyncImage(
                model = food.photo.thumb ?: "",
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .width(40.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(4.dp))
                        .border(
                            0.5.dp,
                            MaterialTheme.colorScheme.primaryContainer,
                            RoundedCornerShape(4.dp),
                        ),
            )
        },
    )
    Divider()
}
