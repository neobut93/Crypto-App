package com.cryptoapp.repositories

import com.cryptoapp.models.Crypto
import com.cryptoapp.network.CryptoService
import com.kodeco.android.countryinfo.database.CryptoDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CryptoRepositoryImpl(
    private val service: CryptoService,
    private val cryptoDao: CryptoDao
): CryptoRepository {

    private val _cryptos: MutableStateFlow<List<Crypto>> = MutableStateFlow(emptyList())
    override val cryptos: StateFlow<List<Crypto>> = _cryptos.asStateFlow()

    override suspend fun fetchCryptos() {
        _cryptos.value = emptyList()

        _cryptos.value = try {
            val cryptosResponse = service.getAllCryptos()
            cryptoDao.deleteAllCryptos()
            if (cryptosResponse.isSuccessful) {
               val cryptos =  cryptosResponse.body()!!
                    .toMutableList()
                cryptoDao.addCryptos(cryptos)
                cryptos

            } else {
                throw Throwable("Request failed: ${cryptosResponse.errorBody()}")
            }
        } catch (e: Exception) {
            _cryptos.value = emptyList()
            cryptoDao.getAllCryptos().ifEmpty {
                throw Exception("Request failed: ${e.message}")
            }
        }
    }

    override fun getCrypto(id: Int): Crypto? =
        _cryptos.value.getOrNull(id)
}