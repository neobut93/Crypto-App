package com.cryptoapp.ui.screens.cryptodetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cryptoapp.R
import com.cryptoapp.models.Crypto
import com.cryptoapp.sample.sampleCrypto
import com.cryptoapp.utils.PriceFormatting

@Composable
fun HoursData(crypto: Crypto) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row {
            Text(
                text = "24h data:",
                modifier = Modifier.padding(all = 5.dp),
                fontStyle = FontStyle.Italic
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.max_price),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${PriceFormatting.formatCurrentPrice(crypto.high_24h)}$",
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.price_change),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${PriceFormatting.formatCurrentPrice(crypto.price_change_24h)}$",
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.low_price),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${PriceFormatting.formatCurrentPrice(crypto.low_24h)}$",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun HoursDataPreview() {
    HoursData(crypto = sampleCrypto)
}