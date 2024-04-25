package com.cryptoapp.ui.screens.error

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ErrorScreen(
    error: Throwable,
    onRetry: () -> Unit,
    onAppClose: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Log.e("CryptoErrorScreen", "Error: ${error.message}")
        ErrorDialog(onRetry = onRetry) {
            onAppClose()
        }
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(
        error = Throwable("Error message"),
        onRetry = {},
        onAppClose = {}
    )
}
