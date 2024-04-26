package com.cryptoapp.repositories

import android.util.Log
import com.cryptoapp.models.Crypto
import com.cryptoapp.network.CryptoService
import com.cryptoapp.database.CryptoDao
import com.cryptoapp.preferences.CryptoSharedPrefs
import com.cryptoapp.preferences.CryptoSharedPrefsImpl
import com.cryptoapp.preferences.PreferencesConstants.DATA_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CryptoRepositoryImpl(
    private val service: CryptoService,
    private val cryptoDao: CryptoDao,
    private val prefs: CryptoSharedPrefs
): CryptoRepository {

    private val _cryptos: MutableStateFlow<List<Crypto>> = MutableStateFlow(emptyList())
    override val cryptos: StateFlow<List<Crypto>> = _cryptos.asStateFlow()

    override suspend fun fetchCryptos() {
        Log.d("GGG+Rep", prefs.getData(DATA_KEY, false).toString())
        _cryptos.value = emptyList()

        _cryptos.value = try {
            val cryptosResponse = service.getAllCryptos()
            prefs.saveData(DATA_KEY, false)
            cryptoDao.deleteAllCryptos()
            if (cryptosResponse.isSuccessful) {
                val cryptos =  cryptosResponse.body()!!
                    .toMutableList()
                cryptoDao.addCryptos(cryptos)
                cryptos

            } else {
                prefs.saveData(DATA_KEY, true)
                throw Throwable("Request failed: ${cryptosResponse.errorBody()}")
            }
        } catch (e: Exception) {
            prefs.saveData(DATA_KEY, true)
            _cryptos.value = emptyList()
            cryptoDao.getAllCryptos().ifEmpty {
                prefs.saveData(DATA_KEY, false)
                throw Exception("Request failed: ${e.message}")
            }
        }
    }

    override fun getCrypto(id: Int): Crypto? =
        _cryptos.value.getOrNull(id)
}