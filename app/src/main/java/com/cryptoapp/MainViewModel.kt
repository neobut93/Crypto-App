package com.cryptoapp

import androidx.lifecycle.ViewModel
import com.cryptoapp.preferences.CryptoSharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val prefs: CryptoSharedPrefs,
) : ViewModel() {

    fun getData(key: String, value: Boolean): Boolean {
        return prefs.getData(key, value)
    }
}