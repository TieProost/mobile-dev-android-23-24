package com.tieproost.fitnessapp.ui.dashboard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tieproost.fitnessapp.model.Settings
import com.tieproost.fitnessapp.ui.util.booleanToSexString
import com.tieproost.fitnessapp.ui.util.nullableToString
import java.time.LocalDate
import java.time.Period

@Composable
fun SettingsCard(settings: Settings) {
    val age =
        if (settings.birthDate == null) {
            " - "
        } else {
            Period.between(settings.birthDate, LocalDate.now()).years.toString()
        }

    Card(
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onSecondary,
            ),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
        ) {
            Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                SettingItem(
                    icon = Icons.Filled.CalendarMonth,
                    title = "Age",
                    value = age,
                )
                SettingItem(
                    icon = Icons.Filled.Person,
                    title = "Sex",
                    value = booleanToSexString(settings.sex),
                )
            }
            Column {
                SettingItem(
                    icon = Icons.Filled.Scale,
                    title = "Weight",
                    value = nullableToString(settings.weight, " - "),
                )
                SettingItem(
                    icon = Icons.Filled.Height,
                    title = "Height",
                    value = nullableToString(settings.height, " - "),
                )
            }
        }
    }
}
