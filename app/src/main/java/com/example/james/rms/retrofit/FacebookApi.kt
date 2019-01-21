package com.example.james.rms.retrofit

import com.example.james.rms.core.model.Facebook

/**
 * Created by steve_000 on 22/1/2019.
 * for project RMS
 * package name com.example.james.rms.retrofit
 */
interface FacebookApi {
    fun findAll(): List<Facebook>
    fun findByFacebookId(facebookSearch_json: String): Facebook
    fun countFacebookId(facebookSearch_json: String): Int?
}