package com.cryptoapp.ui.components

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import co.yml.charts.ui.linechart.LineChart
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cryptoapp.models.Crypto
import com.cryptoapp.utils.PriceFormatting


@Composable
fun CryptoDetails(
    crypto: Crypto,
    modifier: Modifier,
) {
    Column(modifier = modifier.padding(start = 8.dp)) {
        Text(text = "Current price")

        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = "$" + PriceFormatting.formatCurrentPrice(crypto.current_price),
                fontWeight = FontWeight.Bold, fontSize = 45.sp
            )
            Text(
                text = PriceFormatting.formatPercentageChange(crypto.market_cap_change_percentage_24h) + " %",
                fontWeight = FontWeight.Bold, fontSize = 15.sp,
                color = if (crypto.market_cap_change_percentage_24h > 0) {
                    Color(0xFF63B960)
                } else {
                    Color.Red
                },
                modifier = Modifier.padding(start = 5.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color(0xFFCECED5))

        Text(text = "24h data:")

        Row(modifier = Modifier.padding(all = 8.dp)) {

            Column {

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Max price: " + PriceFormatting.formatCurrentPrice(crypto.high_24h) + "$")

                Spacer(modifier = Modifier.height(10.dp))
                // add handler
                Text(text = "Price change: " + PriceFormatting.formatCurrentPrice(crypto.price_change_24h))
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Low price: " + PriceFormatting.formatCurrentPrice(crypto.low_24h) + "$"
                )

            }
            // add prices handler + color
            PerformanceChart(modifier = Modifier
                .width(150.dp).height(100.dp).padding(start = 20.dp)
            )
        }
    }
}
