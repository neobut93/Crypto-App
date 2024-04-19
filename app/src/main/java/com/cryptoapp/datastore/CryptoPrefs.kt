package com.kodeco.android.countryinfo.datastore

import kotlinx.coroutines.flow.Flow

interface CryptoPrefs {
    fun getWelcomeScreenEnabled(): Flow<Boolean>
    suspend fun toggleWelcomeScreen(value: Boolean)
}