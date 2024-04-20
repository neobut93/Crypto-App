package com.cryptoapp.repositories

import com.cryptoapp.models.Crypto
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {
    val cryptos: Flow<List<Crypto>>

    suspend fun fetchCryptos()
    fun getCrypto(id: Int): Crypto?
}