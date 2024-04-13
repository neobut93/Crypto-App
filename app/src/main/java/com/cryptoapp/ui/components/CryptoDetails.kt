package com.cryptoapp.ui.components

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cryptoapp.models.Crypto

@Composable
fun CryptoDetails(
    crypto: Crypto,
    modifier: Modifier,
) {
    LazyColumn(modifier = modifier) {
        item { Text(text = "Capital: ${crypto.symbol}") }
        item { Text(text = "Population: ${crypto.market_cap}") }
        item { Text(text = "Area: ${crypto.price_change_24h}") }
        item {
            var expanded by remember { mutableStateOf(false) }
            val flagTransition = updateTransition(
                targetState = expanded,
                label = "details_transition",
            )
            val widthAnimation by flagTransition.animateDp(
                label = "details_size",
            ) { state ->
                if (state) {
                    300.dp
                } else {
                    150.dp
                }
            }
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(crypto.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "Flag",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .border(1.dp, color = MaterialTheme.colorScheme.primary)
                    .width(widthAnimation)
                    .clickable { expanded = !expanded },
            )
        }
    }
}
