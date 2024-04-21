package com.cryptoapp.network

import com.cryptoapp.adapters.WrappedCryptoList
import com.cryptoapp.models.Crypto
import retrofit2.Response
import retrofit2.http.GET

interface CryptoService {
    @GET("markets?vs_currency=usd")
    @WrappedCryptoList
    suspend fun getAllCryptos(): Response<List<Crypto>>
}