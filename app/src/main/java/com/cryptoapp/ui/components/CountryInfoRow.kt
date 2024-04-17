package com.cryptoapp.ui.components

//import com.cryptoapp.sample.sampleCrypto
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cryptoapp.models.Crypto
import com.cryptoapp.utils.PriceFormatting
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoInfoRow(
    crypto: Crypto,
    onTap: () -> Unit,
) {
    Card(
        onClick = onTap,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 2.dp)
    ) {
        Row(
            //todo add color depends on Theme
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
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
                contentDescription = "Flag",
                modifier = Modifier
                    .width(30.dp)
                    .weight(0.2f)
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
                text = PriceFormatting.formatPercentageChange(crypto.market_cap_change_percentage_24h) + " %",
                modifier = Modifier.weight(0.4f), fontWeight = FontWeight.Bold, fontSize = 18.sp,
                color = if (crypto.market_cap_change_percentage_24h > 0) {
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
//        crypto = Crypto(
//            "Bitcoin",
//            "btc",
//            "url",
//            65498.00,
//            1178.66,
//            12321.00,
//            1293286386553,
//            1
//        )
//    ) {
//
//    }
//}
