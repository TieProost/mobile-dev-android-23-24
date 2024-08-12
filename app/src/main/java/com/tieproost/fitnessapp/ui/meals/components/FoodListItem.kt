package com.tieproost.fitnessapp.ui.meals.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tieproost.fitnessapp.model.Food
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodListItem(food: Food) {
    ListItem(
        headlineText = { Text(food.name) },
        supportingText = { Text("${food.servingQty} x ${food.servingUnit}") },
        trailingContent = { Text("${food.calories.roundToInt()} kcal") },
        leadingContent = {
            AsyncImage(
                model = food.photo,
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
                            RoundedCornerShape(
                                4.dp,
                            ),
                        ),
            )
        },
    )
    Divider()
}
