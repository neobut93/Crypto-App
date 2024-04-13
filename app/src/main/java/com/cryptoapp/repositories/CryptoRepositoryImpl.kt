package com.cryptoapp.repositories

import com.cryptoapp.models.Crypto
import com.cryptoapp.network.CryptoService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CryptoRepositoryImpl(
    private val service: CryptoService,
): CryptoRepository {

    private val _cryptos: MutableStateFlow<List<Crypto>> = MutableStateFlow(emptyList())
    override val cryptos: StateFlow<List<Crypto>> = _cryptos.asStateFlow()

    override suspend fun fetchCryptos() {
        val cryptosResponse = service.getAllCryptos()
        _cryptos.value = emptyList()

        _cryptos.value = try {
            if (cryptosResponse.isSuccessful) {
                cryptosResponse.body()!!
                    .toMutableList()
            } else {
                throw Throwable("Request failed: ${cryptosResponse.message()}")
            }
        } catch (e: Exception) {
            //throw Throwable("Request failed: ${e.message}")
            _cryptos.value
        }
    }

    override fun getCrypto(id: Int): Crypto? =
        _cryptos.value.getOrNull(id)
}