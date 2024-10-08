package com.tieproost.fitnessapp.ui.settings.dialog.dialogcontent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.tieproost.fitnessapp.R
import com.tieproost.fitnessapp.ui.settings.dialog.components.DialogColumn

/**
 * Composable function for displaying calorie goal setting dialog content.
 *
 * @param onValueChange Function to change the value.
 * @param value Current value.
 */
@Composable
fun CalorieGoalDialogContent(
    onValueChange: (String) -> String,
    value: String,
) {
    val focusRequester = remember { FocusRequester() }

    var textFieldValueState by remember {
        mutableStateOf(
            TextFieldValue(
                text = value,
                selection = TextRange(value.length),
            ),
        )
    }

    var isError by remember { mutableStateOf(false) }

    val onTextFieldValueChange = { it: TextFieldValue ->
        val newValue = onValueChange(it.text)
        if (it.text == newValue) {
            textFieldValueState = it.copy(text = newValue)
            isError = false
        } else {
            textFieldValueState = it.copy(text = newValue)
            isError = true
        }
    }

    val keyBoardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)

    DialogColumn(title = stringResource(R.string.set_daily_intake_goal)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            modifier = Modifier.fillMaxWidth(),
        ) {
            OutlinedTextField(
                value = textFieldValueState,
                onValueChange = onTextFieldValueChange,
                keyboardOptions = keyBoardOptions,
                singleLine = true,
                label = { Text(stringResource(R.string.goal)) },
                modifier = Modifier.focusRequester(focusRequester),
                isError = isError,
                supportingText = { if (isError) Text(stringResource(R.string.please_use_only_numbers)) },
            )

            Text(text = stringResource(R.string.kcal))
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}
