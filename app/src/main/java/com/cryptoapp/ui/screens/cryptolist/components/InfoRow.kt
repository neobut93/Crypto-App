package com.cryptoapp.ui.screens.cryptolist.components

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cryptoapp.R
import com.cryptoapp.models.Crypto
import com.cryptoapp.utils.PriceFormatting
import java.time.Duration
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoInfoRow(
    crypto: Crypto,
    onTap: () -> Unit,
    //toast: Boolean
) {
    val orientation = LocalConfiguration.current.orientation
    val context = LocalContext.current
    val customDuration: Long = 1000L // For example, 2000 milliseconds (2 seconds)

   // Log.d("GGG", toast.toString())

    Card(
        onClick = onTap,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = crypto.market_cap_rank.toString(),
                modifier = Modifier.weight(0.12f),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(crypto.image)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.cryptoicon),
                modifier = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    Modifier
                        .size(40.dp)
                } else {
                    Modifier
                        .width(30.dp)
                        .weight(0.2f)
                }
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = crypto.symbol.uppercase(Locale.ROOT),
                modifier = Modifier.weight(0.4f),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "$" + PriceFormatting.formatCurrentPrice(crypto.current_price),
                modifier = Modifier.weight(0.6f), fontWeight = FontWeight.Bold, fontSize = 18.sp
            )
            Text(
                text = PriceFormatting.formatPercentageChange(crypto.price_change_percentage_24h) + " %",
                modifier = Modifier
                    .weight(0.4f)
                    .padding(
                        if (crypto.price_change_percentage_24h < 0) {
                            0.dp
                        } else {
                            6.dp
                        }
                    ), fontWeight = FontWeight.Bold, fontSize = 18.sp,
                color = if (crypto.price_change_percentage_24h > 0) {
                    Color(0xFF63B960)
                } else {
                    Color.Red
                }
            )
        }
    }

}

//@Preview
//@Composable
//fun CryptoInfoRowPreview() {
//    CryptoInfoRow(
//        crypto = sampleCrypto,
//    ) {
//    }
//}
