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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
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
                        .width(dimensionResource(R.dimen.icon_large))
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(dimensionResource(R.dimen.rounding_small)))
                        .border(
                            dimensionResource(R.dimen.stroke_xs),
                            MaterialTheme.colorScheme.primaryContainer,
                            RoundedCornerShape(dimensionResource(R.dimen.rounding_small)),
                        ),
            )
        },
    )
}
