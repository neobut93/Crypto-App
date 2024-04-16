package com.cryptoapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptoapp.models.Crypto
import com.cryptoapp.utils.PriceFormatting


@Composable
fun CryptoDetails(
    crypto: Crypto,
    modifier: Modifier,
) {
    Column(modifier = modifier.padding(start = 8.dp)) {
        Text(text = "Current price", fontStyle = FontStyle.Italic)

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

        Text(
            text = "24h data:",
            modifier = Modifier.padding(all = 5.dp),
            fontStyle = FontStyle.Italic
        )

        Row(modifier = Modifier.padding(all = 5.dp)) {

            Column {

                //Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Max price: " + PriceFormatting.formatCurrentPrice(crypto.high_24h) + "$",
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                // todo †otalMarketCap + percentage for cap
                Text(
                    text = "Price change: " + PriceFormatting.formatCurrentPrice(crypto.price_change_24h) + "$",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Low price: " + PriceFormatting.formatCurrentPrice(crypto.low_24h) + "$",
                    fontWeight = FontWeight.Bold
                )

            }
            //todo add handler for landscape mode
            PerformanceChart(
                modifier = Modifier
                    .width(150.dp)
                    .height(100.dp)
                    .padding(start = 20.dp),
                startPrice = crypto.current_price.toFloat() - crypto.price_change_24h.toFloat(),
                highestPrice = crypto.high_24h.toFloat(),
                lowestPrice = crypto.low_24h.toFloat(),
                currentPrice = crypto.current_price.toFloat()
            )
        }

        Divider(color = Color(0xFFCECED5))
        Text(
            text = "Market data:",
            modifier = Modifier.padding(all = 5.dp),
            fontStyle = FontStyle.Italic
        )
        //todo add right alignment for values
            Text(text = "Market Cap Rank: #", modifier = Modifier.weight(0.3f))
            Text(
                text = "${crypto.market_cap_rank}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(0.01f)
            )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Market cap: $" + PriceFormatting.formatCurrentPrice(crypto.market_cap.toDouble()),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Trading Volume: $" + PriceFormatting.formatCurrentPrice(crypto.total_volume.toDouble()),
            fontWeight = FontWeight.Bold
        )
    }
    //todo add calculator
}
