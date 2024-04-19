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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cryptoapp.datastore.PreferencesManager
import com.cryptoapp.ui.components.CryptoWelcomePage
import kotlinx.coroutines.coroutineScope

@Composable
fun CryptoWelcomeScreen(
    navigate: () -> Unit
) {
    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    val data = remember { mutableStateOf(preferencesManager.getData("myKey", true)) }

    Log.d("AAA_WELCOME", data.value.toString())


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
                    preferencesManager.saveData("myKey", false)
                    navigate()
                },
                shape = RoundedCornerShape(size = 6.dp)
            ) {
                Text(text = "Click me")
            }
        }
    }
}


//@Preview
//@Composable
//fun OnBoardingScreenPreview() {
//    CryptoWelcomeScreen()
//}
