package com.example.james.rms.Core.ServePath

import com.example.james.rms.NetWork.ServeProfile

/**
 * Created by jamie on 2017/6/10.
 */

object ReceivingItemServePath {
    val serve = ServeProfile.serve

    fun findReceivingItemByPartyId(): String {
        val path = ServeProfile.re_item_findByPartyId
        return serve + path
    }

    fun save(): String {
        val path = ServeProfile.re_item_save
        return serve + path
    }

    fun saves(): String {
        val path = ServeProfile.re_item_save
        return serve + path
    }

    fun deleteByProductId(): String {
        val path = ServeProfile.re_item_deleteByProductId
        return serve + path
    }

    fun deleteByOrderId(): String {
        val path = ServeProfile.re_item_deleteByOrderId
        return serve + path
    }

    fun delete(): String {
        val path = ServeProfile.re_item_delete
        return serve + path
    }

    fun deletes(): String {
        val path = ServeProfile.re_item_deletes
        return serve + path
    }

    fun deleteByReceivingIds(): String {
        val path = ServeProfile.re_item_deleteByReceivingIds
        return serve + path
    }
}
