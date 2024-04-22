package com.cryptoapp.ui.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cryptoapp.R

@Composable
fun CryptoWelcomePage() {
    Column() {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f),
            painter = painterResource(id = R.drawable.crypto_welcome),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Column(modifier = Modifier
            .padding(15.dp)) {
            Text(
                text = "Crypto App",
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = "This is a crypto app which is used to get latest information about crypto currency market. This app is using coingecko API to fetch latest crypto information. The app is showing top 100 crypto currencies on the market. The prices are present in USD",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(top = 8.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview
@Composable
fun CryptoWelcomePagePreview() {
    CryptoWelcomePage()
}