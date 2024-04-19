package com.cryptoapp.ui.screens.welcome


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cryptoapp.ui.components.CryptoWelcomePage
import kotlinx.coroutines.coroutineScope

@Composable
fun CryptoWelcomeScreen(
    viewModel: WelcomeViewModel
) {
    //val favoritesBoolean by viewModel.getWelcome().collectAsState(initial = true)
    //Log.d("GGG", favoritesBoolean.toString())


    Column(modifier = Modifier.fillMaxSize()) {
        CryptoWelcomePage()
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 15.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    viewModel.setWelcomeScreen(false)
                },
                shape = RoundedCornerShape(size = 6.dp)
            ) {
                Text(text = "Click me")
            }
        }
    }
    //Log.d("GGG", favoritesBoolean.toString())
}


//@Preview
//@Composable
//fun OnBoardingScreenPreview() {
//    CryptoWelcomeScreen()
//}
