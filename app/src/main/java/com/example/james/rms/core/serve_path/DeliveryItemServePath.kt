package com.example.james.rms.core.serve_path

import com.example.james.rms.network.ServeProfile

/**
 * Created by Jamie on 15/6/2017.
 */

object DeliveryItemServePath {

    val serve:String
        get() = ServeProfile.serve

    //    Find
    fun serve_findAll(): String = serve + ServeProfile.DeliveryItem.de_item_findAll


    fun serve_findByPartyIdAndStatus(): String = serve + ServeProfile.DeliveryItem.de_item_findByPartyIdAndStatus

    fun serve_findByOrderIdAndStatus(): String  = serve + ServeProfile.DeliveryItem.de_item_findByOrderIdAndStatus

    //    Save
    fun serve_save(): String = serve + ServeProfile.DeliveryItem.de_item_save

    fun serve_saves(): String = serve + ServeProfile.DeliveryItem.de_item_saves

    fun serve_delete(): String = serve + ServeProfile.DeliveryItem.de_item_delete

}
