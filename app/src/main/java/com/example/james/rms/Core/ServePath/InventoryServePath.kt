package com.example.james.rms.Core.ServePath

import com.example.james.rms.NetWork.ServeProfile

/**
 * Created by jamie on 2017/5/1.
 */

object InventoryServePath {
    val serve: String
        get() = ServeProfile.serve

    fun serve_findByPartyIdAndStatus(): String {
        val path = ServeProfile.inventorySum_findByPartyIdAndStatus
        return serve + path
    }

    fun serve_findByPartyIdAndStatusOrderByProductId(): String {
        val path = ServeProfile.inventorySum_findByPartyIdAndStatusOrderByProductId
        return serve + path
    }

    fun serve_findByPartyIdAndStatusOrderByProductIdAsc(): String {
        val path = ServeProfile.inventory_findByPartyIdAndStatusOrderByProductIdAsc
        return serve + path
    }


}
