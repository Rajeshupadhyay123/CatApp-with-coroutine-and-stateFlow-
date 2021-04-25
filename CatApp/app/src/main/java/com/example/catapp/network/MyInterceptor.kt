package com.example.catapp.network

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request=chain.request()
            .newBuilder()
            .addHeader("x-api-key","e1680123-9c09-43a9-9afb-a04eff3fdf1f")
            .build()
        return chain.proceed(request)
    }
}