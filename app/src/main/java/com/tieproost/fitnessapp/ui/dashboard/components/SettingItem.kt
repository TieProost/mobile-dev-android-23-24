package com.tieproost.fitnessapp.ui.dashboard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.tieproost.fitnessapp.R

@Composable
fun SettingItem(
    title: String,
    value: String,
    icon: ImageVector,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.padding(dimensionResource(R.dimen.spacing_hor_medium)),
            tint = MaterialTheme.colorScheme.secondary,
        )
        Column {
            Text(text = title, color = MaterialTheme.colorScheme.secondary)
            Text(text = value)
        }
    }
}
