package com.cryptoapp.ui.screens.cryptodetails

import com.cryptoapp.models.Crypto

sealed class CryptoDetailsState {
    data object Loading : CryptoDetailsState()
    data class Success(val crypto: Crypto) : CryptoDetailsState()
    data class Error(val error: Throwable) : CryptoDetailsState()
}