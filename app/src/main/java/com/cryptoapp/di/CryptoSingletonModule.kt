package com.cryptoapp.di

import android.content.Context
import com.cryptoapp.adapters.CryptoAdapter
import com.cryptoapp.network.CryptoService
import com.cryptoapp.network.MyInterceptor
import com.cryptoapp.repositories.CryptoRepository
import com.cryptoapp.repositories.CryptoRepositoryImpl
import com.cryptoapp.database.CryptoDatabase
import com.cryptoapp.preferences.CryptoSharedPrefs
import com.cryptoapp.preferences.CryptoSharedPrefsImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CryptoSingletonModule {
    @Provides
    @Singleton
    fun provideCryptoService(): CryptoService {
        val moshi = Moshi.Builder()
            .add(CryptoAdapter())
            .build()

        val client = OkHttpClient.Builder().apply {
            addInterceptor(MyInterceptor())}.build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/coins/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(CryptoService::class.java)
    }

    @Provides
    @Singleton
    fun provideCryptoDatabase(@ApplicationContext applicationContext: Context): CryptoDatabase {
        return CryptoDatabase.buildDatabase(applicationContext)
    }

    @Provides
    @Singleton
    fun provideCryptoSharedPrefs(@ApplicationContext applicationContext: Context): CryptoSharedPrefs {
        return CryptoSharedPrefsImpl(applicationContext)
    }

    @Provides
    @Singleton
    fun providesCryptoRepository(
        service: CryptoService,
        database: CryptoDatabase
    ): CryptoRepository = CryptoRepositoryImpl(service,
        database.cryptoDao()
    )
}