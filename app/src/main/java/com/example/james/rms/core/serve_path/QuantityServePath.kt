package com.example.james.rms.core.serve_path

import com.example.james.rms.network.ServeProfile

/**
 * Created by jamie on 2017/4/23.
 */

object QuantityServePath {
    val serve: String
        get() = ServeProfile.serve

    fun serve_findAll(): String {
        val path = ServeProfile.QuantityProfile.quantity_findAll
        return serve + path
    }

    fun serve_findByPartyId(): String {
        val path = ServeProfile.QuantityProfile.quantity_findByPartyId
        return serve + path
    }

    fun serve_quantityDelete(): String {
        val path = ServeProfile.QuantityProfile.quantity_delete
        return serve + path
    }

    fun serve_save(): String {
        val path = ServeProfile.QuantityProfile.quantity_save
        return serve + path
    }


}
