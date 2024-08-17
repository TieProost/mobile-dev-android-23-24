package com.tieproost.fitnessapp.ui.common.mutatedialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.tieproost.fitnessapp.R

@Composable
fun MutateDialogTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isError: Boolean,
) {
    Column(
        modifier =
            Modifier.padding(
                horizontal = dimensionResource(R.dimen.padding_large),
                vertical = dimensionResource(R.dimen.padding_medium),
            ),
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.textfield_medium)),
            placeholder = { Text(text = placeholder) },
            supportingText = {
                if (isError) Text(text = stringResource(R.string.no_match_found))
            },
            isError = isError,
        )
    }
}
