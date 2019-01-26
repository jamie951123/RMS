package com.example.james.rms.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by steve_000 on 24/1/2019.
 * for project RMS
 * package name com.example.james.rms.network
 */
class MyLoggingInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()

        val a =req.url().url().toString()
        if(a.contains("image")){

            val aaa = "54sa5s"
        }



        return chain.proceed(req)
    }
}