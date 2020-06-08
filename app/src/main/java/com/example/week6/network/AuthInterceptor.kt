package com.example.week6.network

import com.example.week6.configure.Constant
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {



    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("api_key", Constant.API_KEY).build()
        val response = request.newBuilder().url(url).build()
        return chain.proceed(response)
    }

}
