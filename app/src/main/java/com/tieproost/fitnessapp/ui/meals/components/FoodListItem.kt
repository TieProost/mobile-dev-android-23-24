package com.tieproost.fitnessapp.ui.meals.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tieproost.fitnessapp.R
import com.tieproost.fitnessapp.model.Food
import kotlin.math.roundToInt

@Composable
fun FoodListItem(food: Food) {
    ListItem(
        headlineContent = { Text(food.name) },
        supportingContent = { Text("${food.servingQty} x ${food.servingUnit}") },
        trailingContent = { Text("${food.calories.roundToInt()} ${stringResource(R.string.kcal)}") },
        leadingContent = {
            AsyncImage(
                model = food.photo,
                contentDescription = food.name + stringResource(R.string.photo),
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
}
