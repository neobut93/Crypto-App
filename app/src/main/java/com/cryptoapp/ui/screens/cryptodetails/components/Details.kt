package com.cryptoapp.ui.screens.cryptodetails.components

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cryptoapp.models.Crypto
import com.cryptoapp.sample.sampleCrypto


@Composable
fun CryptoDetails(
    crypto: Crypto,
    modifier: Modifier,
) {
    LazyColumn(modifier = modifier.padding(start = 8.dp)) {
        item {
            CurrentData(crypto = crypto)
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = Color(0xFFCECED5))
        }

        item {
            Row {
                HoursData(crypto)
                PerformanceChart(
                    modifier = Modifier
                        .width(230.dp)
                        .height(120.dp)
                        .padding(start = 20.dp, bottom = 5.dp, top = 10.dp, end = 10.dp),
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
