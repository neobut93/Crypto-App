package com.cryptoapp.ui.screens.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cryptoapp.ui.animations.shimmer

@Composable
fun CryptoInfoLoadingRow() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 1.dp)
            .shimmer(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.padding(all = 0.2.dp)) {
                Text(text = "")
                Text(text = "")
            }
        }
    }
}

@Preview
@Composable
fun CountryInfoLoadingRowPreview() {
    CryptoInfoLoadingRow()
}
