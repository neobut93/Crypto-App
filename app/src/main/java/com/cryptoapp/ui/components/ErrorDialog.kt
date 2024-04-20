package com.cryptoapp.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorDialog(
    onRetry: () -> Unit,
    onAppClose: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(text = "Error")
        },
        text = {
            Text("Sorry, there is an error. Check your connection and try again")
        },
        confirmButton = {
            Button(
                onClick = onRetry
            ) {
                Text("Try again")
            }
        },
        dismissButton = {
            Button(
                onClick = onAppClose
            ) {
                Text("Close the app")
            }
        }
    )
}
