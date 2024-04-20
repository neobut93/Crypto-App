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
import androidx.compose.ui.unit.dp
import com.cryptoapp.R
import com.cryptoapp.models.Crypto
import com.cryptoapp.utils.PriceFormatting

@Composable
fun MarketsData(crypto: Crypto) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        Text(
            text = stringResource(R.string.markets_data),
            modifier = Modifier.padding(all = 5.dp),
            fontStyle = FontStyle.Italic,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.market_cap_rank),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "#${crypto.market_cap_rank}",
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
                text = stringResource(R.string.market_cap),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$${PriceFormatting.formatCurrentPrice(crypto.market_cap.toDouble())}",
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
                text = stringResource(R.string.trading_volume),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$${PriceFormatting.formatCurrentPrice(crypto.market_cap.toDouble())}",

//                text = "$${PriceFormatting.formatCurrentPrice(crypto.total_volume.toDouble())}",
                fontWeight = FontWeight.Bold
            )
        }
    }
}