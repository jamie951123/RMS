package com.example.james.rms.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.network
 */
object RetrofitUtil {

    private var retrofit:Retrofit? = null
    @JvmStatic
    fun getDefaultRetrofit(): Retrofit {
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                    .baseUrl(ServeProfile.serve)
                    .client(OkhttpUtil.getDefaultClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit!!
    }
}