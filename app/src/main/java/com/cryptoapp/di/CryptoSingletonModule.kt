package com.cryptoapp.di

import android.content.Context
import com.cryptoapp.network.CryptoService
import com.cryptoapp.network.MyInterceptor
import com.cryptoapp.repositories.CryptoRepository
import com.cryptoapp.repositories.CryptoRepositoryImpl
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
            //.add(CountryAdapter())
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

//    @Provides
//    @Singleton
//    fun provideCountryDatabase(@ApplicationContext applicationContext: Context): CountryDatabase {
//        return CountryDatabase.buildDatabase(applicationContext)
//    }

//    @Provides
//    @Singleton
//    fun provideCountryPrefs(@ApplicationContext applicationContext: Context
//    ): CountryPrefs = CountryPrefsImpl(applicationContext)


    @Provides
    @Singleton
    fun providesCryptoRepository(
        service: CryptoService,
    ): CryptoRepository = CryptoRepositoryImpl(service)
}