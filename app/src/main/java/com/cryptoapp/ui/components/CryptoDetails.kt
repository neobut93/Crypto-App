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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
    }
//    LazyColumn(modifier = modifier.padding(start = 10.dp)) {
//        item {
//
//        }
//
//
//        item { Text(text = "Population: ${crypto.market_cap}") }
//        item { Text(text = "Area: ${crypto.price_change_24h}") }
//        item {
//            var expanded by remember { mutableStateOf(false) }
//            val flagTransition = updateTransition(
//                targetState = expanded,
//                label = "details_transition",
//            )
//            val widthAnimation by flagTransition.animateDp(
//                label = "details_size",
//            ) { state ->
//                if (state) {
//                    300.dp
//                } else {
//                    150.dp
//                }
//            }
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(crypto.image)
//                    .crossfade(true)
//                    .build(),
//                contentDescription = "Flag",
//                contentScale = ContentScale.FillWidth,
//                modifier = Modifier
//                    .border(1.dp, color = MaterialTheme.colorScheme.primary)
//                    .width(widthAnimation)
//                    .clickable { expanded = !expanded },
//            )
//        }
//    }
}
