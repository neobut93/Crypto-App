package com.cryptoapp.network

import com.cryptoapp.models.Crypto
import retrofit2.Response
import retrofit2.http.GET

interface CryptoService {
    @GET("markets?vs_currency=usd")
    //@WrappedCountryList
    suspend fun getAllCryptos(): Response<List<Crypto>>
}