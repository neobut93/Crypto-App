package com.cryptoapp.ui.screens.cryptolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoapp.preferences.CryptoSharedPrefs
import com.cryptoapp.repositories.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val repository: CryptoRepository,
    private val prefs: CryptoSharedPrefs,
    ) : ViewModel() {

    private val _uiState = MutableStateFlow<CryptoListState>(CryptoListState.Loading)

    val uiState: StateFlow<CryptoListState> = _uiState.asStateFlow()

    private val _refreshState = MutableStateFlow(false)

    val refreshState: StateFlow<Boolean> = _refreshState.asStateFlow()

    private val _countrySharedFlow = MutableSharedFlow<String>()
    val countrySharedFlow = _countrySharedFlow.asSharedFlow()


    init {
        viewModelScope.launch {
            repository
                .cryptos
                .catch {
                    _uiState.value = CryptoListState.Error(it)
                }
                .collect {
                    _uiState.value = CryptoListState.Success(it)
                }
        }
        fetchCryptos()
    }

    fun fetchCryptos() {

        _refreshState.value = true

        _uiState.value = CryptoListState.Loading

        viewModelScope.launch {
            try {
                repository.fetchCryptos()
            } catch (e: Exception) {
                _uiState.value = CryptoListState.Error(e)
            }
        }
        _refreshState.value = false
    }

    fun getData(key: String, value: Boolean): Boolean {
        return prefs.getData(key, value)
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun triggerCountrySharedFlow(message: String) {
        GlobalScope.launch {
            _countrySharedFlow.emit(message)
        }
    }
}