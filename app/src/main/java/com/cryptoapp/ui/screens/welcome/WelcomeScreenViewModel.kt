package com.cryptoapp.ui.screens.welcome

import androidx.lifecycle.ViewModel
import com.cryptoapp.preferences.CryptoSharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeScreenViewModel @Inject constructor(
    private val prefs: CryptoSharedPrefs,
) : ViewModel() {

    fun saveData(key: String, value: Boolean) {
        prefs.saveData(key, value)
    }
}