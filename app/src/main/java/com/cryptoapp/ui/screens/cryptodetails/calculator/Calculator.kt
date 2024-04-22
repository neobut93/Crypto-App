package com.cryptoapp.ui.screens.cryptodetails.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptoapp.models.Crypto
import com.cryptoapp.utils.AutoResizedText
import com.cryptoapp.utils.DataFormatting.formatBuyOption
import com.cryptoapp.utils.DataFormatting.formatSellOption
import java.util.Locale

@Composable
fun CalculatorField(crypto: Crypto) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var inputValue by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("0") }
    val focusManager = LocalFocusManager.current
    var toggle by rememberSaveable { mutableStateOf(false) }
    val limitAmountOfDigits = 6
    val imeAction = if (inputValue != "") {
        ImeAction.Done
    } else {
        ImeAction.None
    }

    Row {
        Text(
            text = "Calculate amount:",
            modifier = Modifier.padding(all = 5.dp),
            fontStyle = FontStyle.Italic,
        )
    }
    Spacer(modifier = Modifier.height(5.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(start = 2.dp, end = 6.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 5.dp)) {
            Text(text = "Buy", fontSize = 30.sp)
            Switch(
                checked = toggle,
                onCheckedChange = {
                    toggle = it
                    result = "0"
                    inputValue = ""
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.secondary,
                    checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                    uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                    uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                thumbContent = {
                    Icon(
                        imageVector = if (toggle) Icons.Filled.Sell else Icons.Filled.MonetizationOn,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize)
                    )
                },
                modifier = Modifier.padding(start = 10.dp, end = 10.dp)
            )
            Text(text = "Sell", fontSize = 30.sp)
        }
        TextField(
            value = inputValue,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    result = if (!toggle) {
                        formatBuyOption(inputValue.toDouble() / crypto.current_price)
                    } else {
                        formatSellOption(crypto.current_price * inputValue.toDouble())
                    }
                    keyboardController?.hide()
                }),
            onValueChange = {
                if (it.length <= limitAmountOfDigits) inputValue = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = {
                if (!toggle) {
                    Text(text = "Enter amount in $")
                } else {
                    Text(text = "Enter ${crypto.symbol.uppercase(Locale.ROOT)} amount")
                }
            },
            singleLine = true
        )
        Row {
            Button(
                onClick = {
                    result = if (!toggle) {
                        formatBuyOption(inputValue.toDouble() / crypto.current_price)
                    } else {
                        formatSellOption(crypto.current_price * inputValue.toDouble())
                    }
                },
                enabled = inputValue != "",
                modifier = Modifier.size(width = 120.dp, height = 40.dp),
                shape = RoundedCornerShape(50)

            ) {
                Text(text = "Calculate")
            }

            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
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
        if (!toggle) {
            Text(text = "Amount of ${crypto.symbol.uppercase(Locale.ROOT)}")
        } else {
            Text(text = "Amount in $")
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 15.dp)
        ) {
            AutoResizedText(
                text = "$result ",
                style = androidx.compose.material.MaterialTheme.typography.h1,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}
