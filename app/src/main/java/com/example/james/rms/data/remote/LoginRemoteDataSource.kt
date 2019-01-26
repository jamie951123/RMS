package com.example.james.rms.data.remote

import com.example.james.rms.core.model.LoginModel
import com.example.james.rms.core.model.UserProfile
import com.example.james.rms.data.LoginDataSource
import com.example.james.rms.network.OkhttpUtil
import com.example.james.rms.network.RetrofitUtil
import com.example.james.rms.network.ServeProfile
import com.example.james.rms.retrofit.UserProfileApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.data.remote
 */
class LoginRemoteDataSource() : LoginDataSource {

    val retrofit = RetrofitUtil.getDefaultRetrofit()

    private val api: UserProfileApi = retrofit.create(UserProfileApi::class.java)

    override fun login(username:String,password:String,callback: LoginDataSource.Callback) {
        val model = UserProfile().apply {
            this.username = username
            this.password = password
        }
        val call = api.checkLogin(model)

        call.enqueue(object :Callback<LoginModel>{
            override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                callback.onFail("")
            }

            override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                // for network error
                if (!response.isSuccessful){
                    callback.onFail("")
                    return
                }
                //
                if(response.body()!!.loginStatus == "LoginSuccessful"){
                    callback.onSuccess()
                    return
                }
                // for username or password error
                if(response.body()!!.loginStatus == "LoginFail"){
                    callback.onFail("LoginFail")
                    return
                }

            }
        })
    }

    override fun registerAccount(username: String, password: String, callback: LoginDataSource.Callback) {
        val model = UserProfile().apply {
            this.username = username
            this.password = password
        }
        val call = api.save(model)
        call.enqueue(object :Callback<UserProfile>{
            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                callback.onFail("")
            }

            override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                // for network error
                if (!response.isSuccessful){
                    callback.onFail("")
                    return
                }
                //
                if(response.body()!!.status == "LoginSuccessful"){
                    callback.onSuccess()
                    return
                }
                // for username or password error
                if(response.body()!!.status == "LoginFail"){
                    callback.onFail("LoginFail")
                    return
                }

            }
        })
    }

    override fun facebookLogin(facebookCallback: LoginDataSource.facebookCallback) {
    }
}