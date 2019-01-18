package com.example.james.rms.core.serve_path

import com.example.james.rms.network.ServeProfile

/**
 * Created by Jamie on 15/6/2017.
 */

object DeliveryOrderServePath {
    val serve:String
        get() = ServeProfile.serve

    //Find
    fun serve_findAll(): String {
        return serve + ServeProfile.de_order_findAll
    }

    fun serve_findByPartyIdAndStatus(): String {
        return serve + ServeProfile.de_order_findByPartyIdAndStatus
    }

    fun serve_findByOrderIdAndStatus(): String {
        return serve + ServeProfile.de_order_findByOrderIdAndStatus
    }

    //Save
    fun serve_save(): String {
        return serve + ServeProfile.de_order_save
    }

    fun serve_saveOrderAndItem(): String {
        return serve + ServeProfile.de_saveOrderAndItem
    }

    //Update


    //Delete
    fun serve_delete(): String {
        return serve + ServeProfile.de_order_delete
    }
}
