package com.example.james.rms.retrofit

import com.example.james.rms.core.model.Facebook
import com.example.james.rms.network.ServeProfile
import retrofit2.Call
import retrofit2.http.POST

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.retrofit
 */
interface FacebookApi {
    @POST(ServeProfile.Facebook.facebook_findAll)
    fun findAll(): Call<List<Facebook>>
    @POST(ServeProfile.Facebook.facebook_findByFacebookId)
    fun findByFacebookId(facebookSearch_json: String): Call<Facebook>
    @POST(ServeProfile.Facebook.facebook_countFacebookId)
    fun countFacebookId(facebookSearch_json: String): Call<Int?>
}