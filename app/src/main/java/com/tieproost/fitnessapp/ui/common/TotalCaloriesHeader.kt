package com.tieproost.fitnessapp.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.tieproost.fitnessapp.R

/**
 * Composable function to display calories aggregate header.
 *
 * @param text [String] for calorie count prefix.
 * @param calories Calorie count.
 */
@Composable
fun TotalCaloriesHeader(
    text: String,
    calories: Int,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text,
            modifier =
                Modifier.padding(
                    top = dimensionResource(R.dimen.spacing_medium),
                    end = dimensionResource(R.dimen.padding_small),
                ),
        )
        Card(
            colors =
                CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_medium)),
        ) {
            Text(
                "$calories ${stringResource(R.string.kcal)} ",
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
