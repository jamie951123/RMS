package com.example.james.rms.Core.ServePath

import com.example.james.rms.NetWork.ServeProfile

/**
 * Created by Jamie on 11/7/2017.
 */

object FacebookServerPath {
    val serve: String
        get() = ServeProfile.serve

    fun serve_findAll(): String {
        return serve + ServeProfile.facebook_findAll
    }

    fun serve_findbyFacebookId(): String {
        return serve + ServeProfile.facebook_findByFacebookId
    }

    fun serve_countFacebookId(): String {
        return serve + ServeProfile.facebook_countFacebookId
    }
}
