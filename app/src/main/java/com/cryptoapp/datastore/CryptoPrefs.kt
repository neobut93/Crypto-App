package com.kodeco.android.countryinfo.datastore

import kotlinx.coroutines.flow.Flow

interface CryptoPrefs {
    fun getWelcomeScreen(): Flow<Boolean>
    suspend fun disableWelcomeScreen(value: Boolean)
}