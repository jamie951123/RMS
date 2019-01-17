package com.example.james.rms.Core.ServePath

import com.example.james.rms.NetWork.ServeProfile

/**
 * Created by jamie on 2017/4/23.
 */

object QuantityServePath {
    val serve: String
        get() = ServeProfile.serve

    fun serve_findAll(): String {
        val path = ServeProfile.quantity_findAll
        return serve + path
    }

    fun serve_findByPartyId(): String {
        val path = ServeProfile.quantity_findByPartyId
        return serve + path
    }

    fun serve_quantityDelete(): String {
        val path = ServeProfile.quantity_delete
        return serve + path
    }

    fun serve_save(): String {
        val path = ServeProfile.quantity_save
        return serve + path
    }

    //    public static String serve_updateQtyByQuantityIdAndPartyIdAndQtyUnit(){
    //        String serve = ServeProfile.getServe();
    //        String path = ServeProfile.getQuantity_updateQtyByQuantityIdAndPartyIdAndQtyUnit();
    //        String serve_path = serve+path;
    //        return serve_path;
    //    }

}
