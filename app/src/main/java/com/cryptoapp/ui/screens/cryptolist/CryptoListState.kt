package com.cryptoapp.ui.screens.cryptolist

import com.cryptoapp.models.Crypto

sealed class CryptoListState {
    data object Loading : CryptoListState()
    data class Success(val countries: List<Crypto>) : CryptoListState()
    data class Error(val error: Throwable) : CryptoListState()
}