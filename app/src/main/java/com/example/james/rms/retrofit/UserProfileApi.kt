package com.example.james.rms.retrofit

import com.example.james.rms.core.model.LoginModel
import com.example.james.rms.core.model.UserProfile
import com.example.james.rms.network.ServeProfile
import retrofit2.Call
import retrofit2.http.POST

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.retrofit
 */
interface UserProfileApi {
    //    Find
    @POST(ServeProfile.Login.login_findAll)
    fun findAll(): Call<List<UserProfile>>
    @POST(ServeProfile.Login.login_findByPartyId)
    fun findByPartyId(userProfileSearchObject_json: String): Call<UserProfile>
    @POST(ServeProfile.Login.login_findByFacebookId)
    fun findByFacebookId(userProfileSearchObject_json: String): Call<UserProfile>
    @POST(ServeProfile.Login.login_checklogin)
    fun checkLogin(userProfile_json: String): Call<LoginModel>

    //    Save
    @POST(ServeProfile.Login.login_save)
    fun save(userProfile_json: String): Call<UserProfile>

}