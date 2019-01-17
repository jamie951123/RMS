package com.example.james.rms.Core.ServePath

import com.example.james.rms.NetWork.ServeProfile

/**
 * Created by jamie on 2017/4/23.
 */

object WeightServePath {
    val serve = ServeProfile.serve

    fun serve_findAll(): String {
        val path = ServeProfile.weight_findAll
        return serve + path
    }

    fun serve_findByPartyId(): String {
        val path = ServeProfile.weight_findByPartyId
        return serve + path
    }

    fun serve_delete(): String {
        val path = ServeProfile.weight_delete
        return serve + path
    }

    fun serve_save(): String {
        val path = ServeProfile.weight_save
        return serve + path
    }

}
