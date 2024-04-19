package com.cryptoapp.ui.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptoapp.datastore.CryptoPrefsImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val prefs: CryptoPrefsImpl,
) : ViewModel() {

    fun getWelcome(): Flow<Boolean> {
        return prefs.getWelcomeScreenEnabled()
    }

    fun setWelcomeScreen(isSet: Boolean) {
        viewModelScope.launch {
            prefs.toggleWelcomeScreen(isSet)
        }
    }
}