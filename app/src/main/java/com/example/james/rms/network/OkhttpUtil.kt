package com.example.james.rms.network

import okhttp3.OkHttpClient

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.network
 */
object OkhttpUtil {

    private var client:OkHttpClient? = null
    @JvmStatic
    fun getDefaultClient(): OkHttpClient {
        if(client == null){
            client = OkHttpClient.Builder()
                    .addInterceptor(MyLoggingInterceptor())
                    .build()
        }
        return client!!
    }
}