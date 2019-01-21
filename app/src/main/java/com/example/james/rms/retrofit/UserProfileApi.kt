package com.example.james.rms.retrofit

import com.example.james.rms.core.model.LoginModel
import com.example.james.rms.core.model.UserProfile
import com.example.james.rms.core.search_object.UserProfileSearchObject
import com.example.james.rms.network.ServeProfile
import retrofit2.Call
import retrofit2.http.Body
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
    fun findByPartyId(@Body userProfileSearchObject: UserProfileSearchObject): Call<UserProfile>
    @POST(ServeProfile.Login.login_findByFacebookId)
    fun findByFacebookId(@Body userProfileSearchObject: UserProfileSearchObject): Call<UserProfile>

    @POST(ServeProfile.Login.login_checklogin)
    fun checkLogin(@Body userProfile: UserProfile): Call<LoginModel>
    //    Save
    @POST(ServeProfile.Login.login_save)
    fun save(@Body userProfile: UserProfile): Call<UserProfile>

}