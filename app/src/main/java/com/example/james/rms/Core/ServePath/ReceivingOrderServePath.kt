package com.example.james.rms.Core.ServePath

import com.example.james.rms.NetWork.ServeProfile

/**
 * Created by james on 26/3/2017.
 */
object ReceivingOrderServePath {
    val serve = ServeProfile.serve


    fun findByPartyId(): String {
        val path = ServeProfile.re_order_findByPartyId
        return serve + path
    }

    fun findByPartyIdAndStatus(): String {
        val path = ServeProfile.re_order_findByPartyIdAndStatus
        return serve + path
    }

    fun findByOrderIdAndStatus(): String {
        val path = ServeProfile.re_order_findByOrderIdAndStatus
        return serve + path
    }

    fun save(): String {
        val path = ServeProfile.re_order_save
        return serve + path
    }

    //    public static String saves(){
    //        String serve = ServeProfile.getServe();
    //        String path = ServeProfile.getRe_order_saves();
    //        String serve_path = serve+path;
    //        return serve_path;
    //    }

    fun saveOrderAndItem(): String {
        val path = ServeProfile.re_saveOrderAndItem
        return serve + path
    }

    fun delete(): String {
        val path = ServeProfile.re_order_delete
        return serve + path
    }

}