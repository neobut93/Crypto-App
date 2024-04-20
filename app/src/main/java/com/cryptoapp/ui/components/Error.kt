package com.cryptoapp.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorScreen(
    //userFriendlyMessageText: String,
    error: Throwable,
    onRetry: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Log.e("CryptoErrorScreen", "Error: ${error.message}")
        Text(
            text = "Error",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
        )

        onRetry?.let {
            Button(
                onClick = onRetry,
                modifier = Modifier.padding(top = 16.dp),
            ) {
                Text(text = "button")
            }
        }
    }
}

//@Preview
//@Composable
//fun ErrorPreview() {
//    MyApplicationTheme {
//        Error(
//            userFriendlyMessageText = "Error message",
//            error = Throwable("Error message"),
//            onRetry = {},
//        )
//    }
//}
