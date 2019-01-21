package com.example.james.rms.core.serve_path

import com.example.james.rms.network.ServeProfile

/**
 * Created by jamie on 2017/5/1.
 */

object InventoryServePath {
    val serve: String
        get() = ServeProfile.serve

    fun serve_findByPartyIdAndStatus(): String {
        val path = ServeProfile.InventorySum.inventorySum_findByPartyIdAndStatus
        return serve + path
    }

    fun serve_findByPartyIdAndStatusOrderByProductId(): String {
        val path = ServeProfile.InventorySum.inventorySum_findByPartyIdAndStatusOrderByProductId
        return serve + path
    }

    fun serve_findByPartyIdAndStatusOrderByProductIdAsc(): String {
        val path = ServeProfile.Inventory.inventory_findByPartyIdAndStatusOrderByProductIdAsc
        return serve + path
    }


}
