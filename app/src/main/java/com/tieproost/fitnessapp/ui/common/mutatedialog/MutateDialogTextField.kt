package com.tieproost.fitnessapp.ui.common.mutatedialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MutateDialogTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isError: Boolean,
) {
    Column(modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
            placeholder = { Text(text = placeholder) },
            supportingText = {
                if (isError) Text(text = "No match found.")
            },
            isError = isError,
        )
    }
}
