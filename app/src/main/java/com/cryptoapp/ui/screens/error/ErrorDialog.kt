package com.cryptoapp.ui.screens.error

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cryptoapp.R

@Composable
fun ErrorDialog(
    onRetry: () -> Unit,
    onAppClose: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(text = stringResource(R.string.error))
        },
        text = {
            Text(stringResource(R.string.sorry_there_is_an_error))
        },
        confirmButton = {
            Button(
                onClick = onRetry
            ) {
                Text(
                    stringResource(R.string.try_again),
                    color = MaterialTheme.colorScheme.inversePrimary
                )
            }
        },
        dismissButton = {
            Button(
                onClick = onAppClose
            ) {
                Text(
                    stringResource(R.string.close_the_app),
                    color = MaterialTheme.colorScheme.inversePrimary
                )
            }
        }
    )
}

@Preview
@Composable
fun ErrorDialogPreview() {
    ErrorDialog(onRetry = {}) {}
}
