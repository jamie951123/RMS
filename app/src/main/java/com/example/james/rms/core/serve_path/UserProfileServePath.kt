package com.example.james.rms.core.serve_path

import com.example.james.rms.network.ServeProfile

/**
 * Created by Jamie on 16/4/2017.
 */

object UserProfileServePath {
    val serve = ServeProfile.serve

    fun serve_checkLogin(): String {
        val path = ServeProfile.Login.login_checklogin
        return serve + path
    }

    fun serve_findAll(): String {
        val path = ServeProfile.Login.login_findAll
        return serve + path
    }

    fun serve_findByPartyId(): String {
        val path = ServeProfile.Login.login_findByPartyId
        return serve + path
    }

    fun serve_findbyFacebookId(): String {
        val path = ServeProfile.Login.login_findByFacebookId
        return serve + path
    }

    fun serve_save(): String {
        val path = ServeProfile.Login.login_save
        return serve + path
    }

}
