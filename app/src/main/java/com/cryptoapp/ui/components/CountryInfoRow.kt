package com.cryptoapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cryptoapp.models.Crypto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoInfoRow(
    crypto: Crypto,
    onTap: () -> Unit,
) {
    Card(
        onClick = onTap,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.padding(all = 8.dp)) {
                Text(text = "Name: ${crypto.symbol}")
                Text(text = "Capital: ${crypto.current_price}")
            }
        }
    }
}
