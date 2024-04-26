package com.cryptoapp.preferences

interface CryptoSharedPrefs {

    fun saveData(key: String, value: Boolean)
    fun getData(key: String, defaultValue: Boolean): Boolean
}