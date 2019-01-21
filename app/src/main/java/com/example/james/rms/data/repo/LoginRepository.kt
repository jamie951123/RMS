package com.example.james.rms.data.repo

import com.example.james.rms.data.LoginDataSource

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.data.repo
 */
class LoginRepository(
        private val remoteDataSource: LoginDataSource
) :LoginDataSource {

    override fun registerAccount(username: String, password: String, callback: LoginDataSource.Callback) {
        remoteDataSource.registerAccount(username, password, callback)
    }

    override fun login(username: String, password: String, callback: LoginDataSource.Callback) {
        remoteDataSource.login(username, password, callback)
    }

    override fun facebookLogin(facebookCallback: LoginDataSource.facebookCallback) {
        remoteDataSource.facebookLogin(facebookCallback)
    }
}