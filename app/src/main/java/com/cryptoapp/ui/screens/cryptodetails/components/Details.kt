package com.cryptoapp.ui.screens.cryptodetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptoapp.models.Crypto
import com.cryptoapp.sample.sampleCrypto
import com.cryptoapp.utils.PriceFormatting


@Composable
fun CryptoDetails(
    crypto: Crypto,
    modifier: Modifier,
) {
    LazyColumn(modifier = modifier.padding(start = 8.dp)) {
        item {
            Text(text = "Current price", fontStyle = FontStyle.Italic)
        }

        item {
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "$" + PriceFormatting.formatCurrentPrice(crypto.current_price),
                    fontWeight = FontWeight.Bold, fontSize = 45.sp
                )
                Text(
                    text = PriceFormatting.formatPercentageChange(crypto.price_change_percentage_24h) + " %",
                    fontWeight = FontWeight.Bold, fontSize = 15.sp,
                    color = if (crypto.price_change_percentage_24h > 0) {
                        Color(0xFF63B960)
                    } else {
                        Color.Red
                    },
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = Color(0xFFCECED5))
        }

        item {
            Row(){
                HoursData(crypto)
                PerformanceChart(
                    modifier = Modifier
                        .width(230.dp)
                        .height(120.dp)
                        .padding(start = 20.dp, bottom = 5.dp, top =10.dp, end = 10.dp),
                    startPrice = crypto.current_price.toFloat() - crypto.price_change_24h.toFloat(),
                    highestPrice = crypto.high_24h.toFloat(),
                    lowestPrice = crypto.low_24h.toFloat(),
                    currentPrice = crypto.current_price.toFloat()
                )
            }

            Divider(color = Color(0xFFCECED5))
            MarketsData(crypto)
        }

    }
    //todo add calculator
}

@Preview
@Composable
fun CryptoDetailsPreview() {
    CryptoDetails(
        crypto = sampleCrypto,
        modifier = Modifier
    )
}
