package com.tieproost.fitnessapp.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tieproost.fitnessapp.R

@Composable
fun ErrorScreen()  {
    Text(stringResource(R.string.something_went_wrong))
}
