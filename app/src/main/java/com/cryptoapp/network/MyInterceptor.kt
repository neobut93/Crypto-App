package com.cryptoapp.network

import com.cryptoapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor: Interceptor {
    private val apiKey = BuildConfig.API_KEY
    override fun intercept(chain: Interceptor.Chain): Response {
       val request = chain.request()
           .newBuilder()
           .addHeader("accept", "application/json")
           .addHeader("x-cg-demo-api-key", apiKey)
           .build()
        return chain.proceed(request)
    }
}