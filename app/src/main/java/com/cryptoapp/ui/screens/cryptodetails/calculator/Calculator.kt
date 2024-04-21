package com.cryptoapp.ui.screens.cryptodetails.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptoapp.models.Crypto
import com.cryptoapp.utils.DataFormatting.formatCalculator
import java.util.Locale

@Composable
fun CalculatorField(crypto: Crypto) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var inputValue by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("0") }
    val focusManager = LocalFocusManager.current


    val imeAction = if (inputValue != "") {
        ImeAction.Done
    } else {
        ImeAction.None
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(start = 2.dp, end = 6.dp)
    ) {
        TextField(
            value = inputValue,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    result = formatCalculator(inputValue.toDouble() / crypto.current_price)
                    keyboardController?.hide()
                }),
            onValueChange = { inputValue = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = { Text(text = "Enter amount in $") },
            singleLine = true
        )

        Row {
            Button(onClick = {
                result = formatCalculator(inputValue.toDouble() / crypto.current_price)
            },
                enabled = inputValue != "",
                modifier = Modifier.size(width = 120.dp, height = 40.dp),
                shape = RoundedCornerShape(50)

            ) {
                Text(text = "Calculate")
            }

            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = {
                result = "0"
                inputValue = ""
                focusManager.clearFocus()
            },
                enabled = inputValue != "",
                modifier = Modifier.size(width = 120.dp, height = 40.dp),
                shape = RoundedCornerShape(50)

            ) {
                Text(text = "Clear")
            }
        }
        Spacer(modifier = Modifier.size(15.dp))
        Text(text = "Amount of ${crypto.symbol.uppercase(Locale.ROOT)}")
        Text(text = "$result ", fontSize = 40.sp)

    }
    }
