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
        //Spacer(modifier = Modifier.height(MediumPadding1))
        Text(
            modifier = Modifier.padding(horizontal = 5.dp),
            text = "Crypto App",
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            //color = colorResource(id = R.color.display_small)
        )
        Text(
            modifier = Modifier.padding(horizontal = 5.dp),
            text = "That’s not to say it’s gone without incident. In 2012, the price of Bitcoin dropped about 90 percent, and then dropped 80 percent in 2015 and 2019. We appear to be hurtling toward another recession now, and the cryptocurrency market appears to be following suit. But it’s important to remember, all markets have bull and bear cycles. Just because crypto is down right now, does not mean it’s out.",
            style = MaterialTheme.typography.bodyMedium,
            //color = colorResource(id = R.color.text_medium)
        )
    }
}