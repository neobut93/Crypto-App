package com.cryptoapp.network

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
       val request = chain.request()
           .newBuilder()
           .addHeader("accept", "application/json")
           .addHeader("x-cg-demo-api-key", "CG-MmfF97vTxG8pjxzHkMHVUDMi")
           .build()
        return chain.proceed(request)
    }
}