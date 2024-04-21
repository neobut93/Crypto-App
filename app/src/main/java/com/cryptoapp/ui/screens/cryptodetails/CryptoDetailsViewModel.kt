package com.cryptoapp.ui.screens.cryptodetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoapp.repositories.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoDetailsViewModel
@Inject constructor(private val repository: CryptoRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<CryptoDetailsState>(CryptoDetailsState.Loading)

    val uiState: StateFlow<CryptoDetailsState> = _uiState

    fun getCryptoDetails(cryptoId: Int) {
        viewModelScope.launch {
            _uiState.value = CryptoDetailsState.Loading

            _uiState.value = repository.getCrypto(cryptoId)?.let { crypto ->
                CryptoDetailsState.Success(crypto)
            } ?: CryptoDetailsState.Error(Exception("Crypto not found"))
        }
    }
}
