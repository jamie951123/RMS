package com.example.james.rms.data

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.data
 */
interface LoginDataSource {

    fun login(username:String,password:String, callback: Callback)

    fun registerAccount(username:String,password:String, callback: Callback)
    interface Callback{
        fun onSuccess()
        fun onFail(reason:String)
    }
    interface facebookCallback{
        fun onLoginSuccess()

        fun onLoginFail()
    }

    fun facebookLogin(facebookCallback: facebookCallback)
}