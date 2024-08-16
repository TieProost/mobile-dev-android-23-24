package com.tieproost.fitnessapp.ui.meals.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tieproost.fitnessapp.data.database.model.MealType

@Composable
fun MealTypeHeader(
    mealType: MealType,
    openDialog: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp),
    ) {
        Text(text = mealType.name + ":", style = MaterialTheme.typography.titleMedium)
        IconButton(onClick = openDialog) {
            Icon(Icons.Filled.Add, contentDescription = "")
        }
    }
    HorizontalDivider()
}
