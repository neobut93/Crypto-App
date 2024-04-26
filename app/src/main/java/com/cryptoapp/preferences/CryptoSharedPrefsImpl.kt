package com.cryptoapp.preferences

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CryptoSharedPrefsImpl @Inject constructor(@ApplicationContext context: Context) :
    CryptoSharedPrefs {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PreferencesConstants.PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun saveData(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    override fun getData(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }
}