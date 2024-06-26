package com.cryptoapp.ui.screens.cryptodetails.components

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cryptoapp.models.Crypto
import com.cryptoapp.sample.sampleCrypto
import com.cryptoapp.ui.theme.CryptoAppTheme

@Composable
fun CryptoDetails(
    crypto: Crypto,
    modifier: Modifier,
) {

    LazyColumn(modifier = modifier.padding(start = 8.dp)) {
        item {
            CurrentData(crypto = crypto)
            Divider(color = Color(0xFFCECED5))
        }

        item {
            Row(modifier = Modifier.padding(bottom = 10.dp)) {
                Column(modifier = Modifier.weight(0.7f)) {
                    HoursData(crypto)
                }
                Column(modifier = Modifier.weight(0.4f)) {
                    PerformanceChart(
                        modifier = Modifier
                            .width(230.dp)
                            .height(120.dp)
                            .padding(start = 4.dp, bottom = 5.dp, top = 10.dp, end = 10.dp),
                        startPrice = crypto.current_price.toFloat() - crypto.price_change_24h.toFloat(),
                        highestPrice = crypto.high_24h.toFloat(),
                        lowestPrice = crypto.low_24h.toFloat(),
                        currentPrice = crypto.current_price.toFloat()
                    )
                }
            }
            Divider(color = Color(0xFFCECED5))
        }

        item {
            MarketsData(crypto)
            Divider(color = Color(0xFFCECED5))
        }

        item {
            CalculatorField(crypto = crypto)
        }
    }
}

@Preview
@Composable
fun CryptoDetailsPreview() {
    CryptoAppTheme(darkTheme = false) {
        CryptoDetails(
            crypto = sampleCrypto,
            modifier = Modifier
        )
    }
}
