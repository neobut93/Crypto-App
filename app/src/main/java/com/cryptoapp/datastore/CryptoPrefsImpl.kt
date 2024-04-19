package com.cryptoapp.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.kodeco.android.countryinfo.datastore.CryptoPrefs
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private const val STORE_NAME = "crypto_prefs"
private val Context.dataStore by preferencesDataStore(name = STORE_NAME)

class CryptoPrefsImpl @Inject constructor(@ApplicationContext context: Context) : CryptoPrefs {

    private val welcomeKey = booleanPreferencesKey("database_key")

    private val dataStore = context.dataStore

    // add int instead of bool
    override fun getWelcomeScreen(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { pref ->
                val isDatabase = pref[welcomeKey] ?: true
                isDatabase
            }
    }

    override suspend fun disableWelcomeScreen(value: Boolean) {
        dataStore.edit { pref ->
            pref[welcomeKey] = value
        }
    }
}