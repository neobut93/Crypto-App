package com.cryptoapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CryptoInfoLoadingList() {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            CryptoInfoLoadingRow()
            CryptoInfoLoadingRow()
            CryptoInfoLoadingRow()
            CryptoInfoLoadingRow()
            CryptoInfoLoadingRow()
            CryptoInfoLoadingRow()
            CryptoInfoLoadingRow()
            CryptoInfoLoadingRow()
            CryptoInfoLoadingRow()
            CryptoInfoLoadingRow()
            CryptoInfoLoadingRow()
            CryptoInfoLoadingRow()
            CryptoInfoLoadingRow()
            CryptoInfoLoadingRow()
        }
    }
}

@Preview
@Composable
fun CountryInfoLoadingListPreview() {
    CryptoInfoLoadingList()
}
