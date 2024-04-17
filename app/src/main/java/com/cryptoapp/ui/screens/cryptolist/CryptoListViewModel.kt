package com.cryptoapp.ui.screens.cryptolist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoapp.repositories.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val repository: CryptoRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<CryptoListState>(CryptoListState.Loading)

    val uiState: StateFlow<CryptoListState> = _uiState.asStateFlow()

    var isRefreshing by mutableStateOf(false)
        private set

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
        isRefreshing = true

        _uiState.value = CryptoListState.Loading

        viewModelScope.launch {
            try {
                repository.fetchCryptos()
            } catch (e: Exception) {
                _uiState.value = CryptoListState.Error(e)
            }
        }
        isRefreshing = false
    }
}