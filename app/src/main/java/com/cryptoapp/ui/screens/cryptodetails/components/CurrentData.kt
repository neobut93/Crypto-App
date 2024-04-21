package com.cryptoapp.ui.screens.cryptodetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptoapp.R
import com.cryptoapp.models.Crypto
import com.cryptoapp.utils.PriceFormatting

@Composable
fun CurrentData(crypto: Crypto) {
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        Text(
            text = stringResource(R.string.current_price),
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(all = 5.dp)
        )
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = "$${PriceFormatting.formatCurrentPrice(crypto.current_price)}",
                fontWeight = FontWeight.Bold, fontSize = 45.sp
            )
            Text(
                text = "${PriceFormatting.formatPercentageChange(crypto.price_change_percentage_24h)} %",
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
}