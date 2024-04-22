package com.cryptoapp

import com.cryptoapp.models.Crypto
import com.cryptoapp.repositories.CryptoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockRepository : CryptoRepository {

    private val fakeCryptos = mutableListOf<Crypto>()

    override val cryptos: Flow<List<Crypto>>
        get() = flow { emit(fakeCryptos) }

    override suspend fun fetchCryptos() {
        fakeCryptos
    }

    override fun getCrypto(id: Int): Crypto? {
        return fakeCryptos[id]
    }
}